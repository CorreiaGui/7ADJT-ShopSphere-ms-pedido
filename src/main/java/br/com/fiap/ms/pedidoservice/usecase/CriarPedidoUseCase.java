package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.ItemPedidoRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.ClienteJsonResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.service.ClienteService;
import br.com.fiap.ms.pedidoservice.gateway.external.estoque.service.EstoqueService;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service.PagamentoService;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.response.ProdutoResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.service.ProdutoService;
import br.com.fiap.ms.pedidoservice.utils.PedidoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.fiap.ms.pedidoservice.utils.PedidoUtils.gerarNumeroPedidoAleatorio;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class CriarPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final EstoqueService estoqueService;
    private final PagamentoService pagamentoService;

    public PedidoEntity criarPedido(PedidoRequestJson pedidoRequestJson) {
        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal valorTotal = ZERO;
        int numeroPedido = gerarNumeroPedidoAleatorio();

        for (ItemPedidoRequestJson itemDTO : pedidoRequestJson.itens()) {
            ProdutoResponse produto = produtoService.buscarPorSku(itemDTO.sku());

            estoqueService.baixarEstoque(itemDTO.sku(), itemDTO.quantidade());

            BigDecimal subtotal = produto.preco().multiply(valueOf(itemDTO.quantidade()));
            valorTotal = valorTotal.add(subtotal);

            ItemPedido item = ItemPedido.builder()
                    .sku(itemDTO.sku())
                    .quantidade(Math.toIntExact(itemDTO.quantidade()))
                    .numeroPedido(numeroPedido)
                    .build();

            itens.add(item);
        }

        ClienteJsonResponse cliente = clienteService.buscarClientesPorCpf(pedidoRequestJson.documentoCliente());

        Pedido pedido = Pedido.builder()
                .id(UUID.randomUUID())
                .itens(itens)
                .cpf(cliente.cpf())
                .dataCriacao(now())
                .valorTotal(valorTotal)
                .numeroPedido(numeroPedido)
                .status(StatusPedido.ABERTO)
                .build();

        PagamentoRequest pagamentoRequest = new PagamentoRequest(
                pedido.getId(),
                pedidoRequestJson.formaPagamento(),
                pedidoRequestJson.numeroCartaoCredito(),
                valorTotal);

        try {
            String pagamentoId = pagamentoService.criarPagamento(pagamentoRequest);
            pedido.setPagamentoId(UUID.fromString(pagamentoId.replace("\"", "")));
        } catch (Exception e) {
            itens.forEach(item ->
                    estoqueService.reporEstoque(item.getSku(), Long.valueOf(item.getQuantidade()))
            );
            throw new RuntimeException("Erro ao processar pagamento: " + e.getMessage(), e);
        }

        PedidoEntity entity = PedidoUtils.convertToPedidoEntity(pedido);
        return pedidoGateway.salvar(entity);
    }

}