package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.external.ClienteServiceClient;
import br.com.fiap.ms.pedidoservice.gateway.external.EstoqueServiceClient;
import br.com.fiap.ms.pedidoservice.gateway.external.PagamentoServiceClient;
import br.com.fiap.ms.pedidoservice.gateway.external.ProdutoServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável por processar pedidos.
 * Verifica a validade do cliente, produtos, processa o pagamento e verifica o estoque.
 * Atualiza o status do pedido conforme o resultado do processamento.
 */
@Service
public class ProcessarPedidoService implements ProcessarPedidoUseCase {

    private final ClienteServiceClient clienteClient;
    private final ProdutoServiceClient produtoClient;
    private final EstoqueServiceClient estoqueClient;
    private final PagamentoServiceClient pagamentoClient;
    private final PedidoPersistenceService pedidoPersistenceService;

    public ProcessarPedidoService(
            ClienteServiceClient clienteClient,
            ProdutoServiceClient produtoClient,
            EstoqueServiceClient estoqueClient,
            PagamentoServiceClient pagamentoClient, PedidoPersistenceService pedidoPersistenceService) {
        this.clienteClient = clienteClient;
        this.produtoClient = produtoClient;
        this.estoqueClient = estoqueClient;
        this.pagamentoClient = pagamentoClient;
        this.pedidoPersistenceService = pedidoPersistenceService;
    }

    @Override
    public void processar(Pedido pedido) {
        pedido.setStatus("ABERTO");
        System.out.println("Processando pedido: " + pedido);

        if (!clienteClient.clienteExiste(pedido.getIdCliente())) {
            pedido.setStatus("CLIENTE_INVALIDO");
            return;
        }

        List<String> skus = pedido.getItens().stream()
                .map(ItemPedido::getSku)
                .toList(); // use Collectors.toList() se estiver com Java 8

        if (!produtoClient.produtosValidos(skus)) {
            pedido.setStatus("PRODUTO_INVALIDO");
            return;
        }

        boolean pagamentoAprovado = pagamentoClient.processarPagamento(pedido);
        boolean estoqueDisponivel = estoqueClient.verificarEstoque(pedido);

        if (!estoqueDisponivel) {
            if (pagamentoAprovado) {
                pagamentoClient.estornarPagamento(pedido);
            }
            pedido.setStatus("FECHADO_SEM_ESTOQUE");
            return;
        }

        if (!pagamentoAprovado) {
            pedido.setStatus("FECHADO_SEM_CREDITO");
            return;
        }

        pedido.setStatus("FECHADO_COM_SUCESSO");
        pedidoPersistenceService.salvar(pedido);
    }

}
