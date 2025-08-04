package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.external.estoque.service.EstoqueService;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.response.PagamentoResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service.PagamentoService;
import br.com.fiap.ms.pedidoservice.controller.json.PagamentoExternoRequest;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamentoExterno.service.PagamentoExternoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessarPagamentoExternoUseCase {

    private final PedidoGateway pedidoGateway;
    private final EstoqueService estoqueService;
    private final PagamentoService pagamentoService;
    private final PagamentoExternoService pagamentoExternoService;

    public String executar(PagamentoExternoRequest pagamentoExternoRequest) {
        try {
            String statusPagamento = pagamentoExternoService.obterStatusPagamento(pagamentoExternoRequest);

            UUID pedidoId = obterPedidoIdPorPagamento(pagamentoExternoRequest);
            PedidoEntity pedido = obterPedido(pedidoId);

            if (!pedido.getStatus().equals(StatusPedido.ABERTO)) {
                String mensagemPedidoFechado = String.format(
                        "Pedido não processado, pois o mesmo se encontra FECHADO.\nNúmero: %d\nStatus atual: %s",
                        pedido.getNumeroPedido(),
                        pedido.getStatus()
                );
                log.warn(mensagemPedidoFechado);
                return mensagemPedidoFechado;
            }

            processarStatusPagamento(statusPagamento, pedido);

            String mensagemBase = String.format(
                    "Número do Pedido: %d\nStatus do Pagamento: %s\nAtualizado em: %s",
                    pedido.getNumeroPedido(),
                    statusPagamento,
                    LocalDateTime.now()
            );

            String mensagemFinal;
            if ("PAGAMENTO_CONFIRMADO".equals(statusPagamento)) {
                mensagemFinal = "Pagamento confirmado com sucesso!\n" + mensagemBase;
            } else if ("PAGAMENTO_REJEITADO_FALTA_CREDITO".equals(statusPagamento)) {
                mensagemFinal = "Pagamento rejeitado por falta de crédito.\n" + mensagemBase;
            } else {
                mensagemFinal = "Pagamento processado com status desconhecido.\n" + mensagemBase;
            }

            return mensagemFinal;

        } catch (Exception ex) {
            log.error("Erro ao processar pagamento externo: {}", ex.getMessage(), ex);
            throw new RuntimeException("Erro ao processar pagamento externo.", ex);
        }
    }

    private UUID obterPedidoIdPorPagamento(PagamentoExternoRequest request) {
        PagamentoResponse pagamento = pagamentoService.buscarPorIdExterno(request.id());
        if (pagamento == null || pagamento.pedidoId() == null) {
            throw new IllegalArgumentException("Pagamento não encontrado ou sem referência ao pedido.");
        }
        return pagamento.pedidoId();
    }

    private PedidoEntity obterPedido(UUID pedidoId) {
        PedidoEntity pedido = pedidoGateway.buscarPorId(pedidoId);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado para ID: " + pedidoId);
        }
        return pedido;
    }

    private void processarStatusPagamento(String statusPagamento, PedidoEntity pedido) {
        switch (statusPagamento) {
            case "PAGAMENTO_CONFIRMADO" -> atualizarStatusPedido(pedido, StatusPedido.FECHADO_COM_SUCESSO);

            case "PAGAMENTO_REJEITADO_FALTA_CREDITO" -> {
                atualizarStatusPedido(pedido, StatusPedido.FECHADO_SEM_CREDITO);
                estornarItensParaEstoque(pedido.getItens());
            }

            case "PAGAMENTO_REJEITADO_SEM_ESTOQUE" -> {
                atualizarStatusPedido(pedido, StatusPedido.FECHADO_SEM_ESTOQUE);
            }

            default -> {
                log.warn("Status de pagamento desconhecido: {}", statusPagamento);
                throw new IllegalStateException("Status de pagamento desconhecido: " + statusPagamento);
            }
        }
    }

    private void atualizarStatusPedido(PedidoEntity pedido, StatusPedido novoStatus) {
        pedido.setStatus(novoStatus);
        pedidoGateway.salvar(pedido);
        log.info("Status do pedido {} atualizado para {}", pedido.getNumeroPedido(), novoStatus);
    }

    private void estornarItensParaEstoque(List<ItemPedidoEntity> itens) {
        if (itens == null || itens.isEmpty()) {
            log.warn("Nenhum item encontrado para estorno no pedido.");
            return;
        }

        for (ItemPedidoEntity item : itens) {
            try {
                estoqueService.reporEstoque(item.getSku(), item.getQuantidade().longValue());
                log.info("Estoque estornado com sucesso: SKU={}, Quantidade={}", item.getSku(), item.getQuantidade());
            } catch (Exception e) {
                log.error("Erro ao estornar item de estoque: SKU={}, Erro={}", item.getSku(), e.getMessage(), e);
            }
        }
    }
}
