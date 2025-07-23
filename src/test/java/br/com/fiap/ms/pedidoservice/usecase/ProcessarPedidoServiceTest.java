package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.gateway.external.ClienteServiceClient;
import br.com.fiap.ms.pedidoservice.gateway.external.EstoqueServiceClient;
import br.com.fiap.ms.pedidoservice.gateway.external.PagamentoServiceClient;
import br.com.fiap.ms.pedidoservice.gateway.external.ProdutoServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProcessarPedidoServiceTest {

    private ClienteServiceClient clienteClient;
    private ProdutoServiceClient produtoClient;
    private EstoqueServiceClient estoqueClient;
    private PagamentoServiceClient pagamentoClient;
    private PedidoPersistenceService persistenceService;
    private ProcessarPedidoService processarPedidoService;

    @BeforeEach
    void setUp() {
        clienteClient = mock(ClienteServiceClient.class);
        produtoClient = mock(ProdutoServiceClient.class);
        estoqueClient = mock(EstoqueServiceClient.class);
        pagamentoClient = mock(PagamentoServiceClient.class);
        persistenceService = mock(PedidoPersistenceService.class);

        processarPedidoService = new ProcessarPedidoService(
                clienteClient,
                produtoClient,
                estoqueClient,
                pagamentoClient,
                persistenceService
        );
    }

    private Pedido criarPedido() {
        Pedido pedido = new Pedido();
        pedido.setIdCliente("123");
        pedido.setNumeroCartao("4111111111111111");
        pedido.setValorTotal(100.0);
        pedido.setItens(Arrays.asList(
                new ItemPedido("SKU1", 2),
                new ItemPedido("SKU2", 1)
        ));
        return pedido;
    }

    @Test
    void deveMarcarPedidoComoClienteInvalido() {
        Pedido pedido = criarPedido();
        when(clienteClient.clienteExiste("123")).thenReturn(false);

        processarPedidoService.processar(pedido);

        assertEquals("CLIENTE_INVALIDO", pedido.getStatus());
        verifyNoInteractions(produtoClient, pagamentoClient, estoqueClient, persistenceService);
    }

    @Test
    void deveMarcarPedidoComoProdutoInvalido() {
        Pedido pedido = criarPedido();
        when(clienteClient.clienteExiste("123")).thenReturn(true);
        when(produtoClient.produtosValidos(Arrays.asList("SKU1", "SKU2"))).thenReturn(false);

        processarPedidoService.processar(pedido);

        assertEquals("PRODUTO_INVALIDO", pedido.getStatus());
        verifyNoInteractions(pagamentoClient, estoqueClient, persistenceService);
    }

    @Test
    void deveMarcarPedidoComoFechadoSemEstoqueEEstornarPagamento() {
        Pedido pedido = criarPedido();
        when(clienteClient.clienteExiste("123")).thenReturn(true);
        when(produtoClient.produtosValidos(Arrays.asList("SKU1", "SKU2"))).thenReturn(true);
        when(pagamentoClient.processarPagamento(pedido)).thenReturn(true);
        when(estoqueClient.verificarEstoque(pedido)).thenReturn(false);

        processarPedidoService.processar(pedido);

        assertEquals("FECHADO_SEM_ESTOQUE", pedido.getStatus());
        verify(pagamentoClient).estornarPagamento(pedido);
        verify(persistenceService, never()).salvar(any());
    }

    @Test
    void deveMarcarPedidoComoFechadoSemCredito() {
        Pedido pedido = criarPedido();
        when(clienteClient.clienteExiste("123")).thenReturn(true);
        when(produtoClient.produtosValidos(Arrays.asList("SKU1", "SKU2"))).thenReturn(true);
        when(pagamentoClient.processarPagamento(pedido)).thenReturn(false);

        processarPedidoService.processar(pedido);

        assertEquals("FECHADO_SEM_ESTOQUE", pedido.getStatus());
        verify(estoqueClient).verificarEstoque(pedido);
        verify(persistenceService, never()).salvar(any());
    }

    @Test
    void deveMarcarPedidoComoFechadoComSucesso() {
        Pedido pedido = criarPedido();
        when(clienteClient.clienteExiste("123")).thenReturn(true);
        when(produtoClient.produtosValidos(Arrays.asList("SKU1", "SKU2"))).thenReturn(true);
        when(pagamentoClient.processarPagamento(pedido)).thenReturn(true);
        when(estoqueClient.verificarEstoque(pedido)).thenReturn(true);

        processarPedidoService.processar(pedido);

        assertEquals("FECHADO_COM_SUCESSO", pedido.getStatus());
        verify(persistenceService).salvar(pedido);
    }
}
