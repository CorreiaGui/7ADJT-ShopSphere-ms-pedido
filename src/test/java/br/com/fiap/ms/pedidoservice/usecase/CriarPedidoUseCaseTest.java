package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.ItemPedidoRequestJson;
import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.ClienteJsonResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.response.EnderecoJsonResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.cliente.service.ClienteService;
import br.com.fiap.ms.pedidoservice.gateway.external.estoque.service.EstoqueService;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service.PagamentoService;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.response.ProdutoResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.produto.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarPedidoUseCaseTest {

    private PedidoGateway pedidoGateway = mock(PedidoGateway.class);
    private ProdutoService produtoService = mock(ProdutoService.class);
    private ClienteService clienteService = mock(ClienteService.class);
    private EstoqueService estoqueService = mock(EstoqueService.class);
    private PagamentoService pagamentoService = mock(PagamentoService.class);

    private CriarPedidoUseCase useCase;

    @BeforeEach
    void setup() {
        useCase = new CriarPedidoUseCase(pedidoGateway, produtoService, clienteService, estoqueService, pagamentoService);
    }

    @Test
    void deveCriarPedidoComSucesso() {
        // Arrange
        String sku = "ABC123";
        String cpf = "12345678900";
        Long quantidade = 2L;

        ProdutoResponse produto = new ProdutoResponse(sku, "Produto Teste", new BigDecimal("10.00"),
                LocalDateTime.now(), LocalDateTime.now());

        EnderecoJsonResponse endereco = new EnderecoJsonResponse(UUID.randomUUID(), "Rua Teste", 123, "Cidade", "SP", "00000-000", null, null, null);
        ClienteJsonResponse cliente = new ClienteJsonResponse(cpf, "Fulano",
                LocalDate.of(2000, 1, 1), LocalDateTime.now(), LocalDateTime.now(), endereco);

        PedidoRequestJson pedidoRequestJson = new PedidoRequestJson(cpf, singletonList(new ItemPedidoRequestJson(sku, quantidade)), 1, "123");

        when(produtoService.buscarPorSku(sku)).thenReturn(produto);
        when(clienteService.buscarClientesPorCpf(cpf)).thenReturn(cliente);
        when(pagamentoService.criarPagamento(any(PagamentoRequest.class)))
                .thenReturn(UUID.randomUUID().toString());
        when(pedidoGateway.salvar(any(PedidoEntity.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        PedidoEntity pedidoCriado = useCase.criarPedido(pedidoRequestJson);

        // Assert
        assertNotNull(pedidoCriado);
        assertEquals(cpf, pedidoCriado.getCpf());
        verify(estoqueService, times(1)).baixarEstoque(eq(sku), eq(quantidade));
        verify(pedidoGateway, times(1)).salvar(any());
    }

    @Test
    void deveEstornarEstoqueQuandoPagamentoFalhar() {
        String sku = "SKU123";
        Long quantidade = 1L;
        String cpf = "32165498700";

        ProdutoResponse produto = new ProdutoResponse(sku, "Produto Quebrado", new BigDecimal("50.00"),
                LocalDateTime.now(), LocalDateTime.now());

        EnderecoJsonResponse endereco = new EnderecoJsonResponse(UUID.randomUUID(), "Av. Falha", 999, "99999-000","Bug City", "RJ", null, null, null);
        ClienteJsonResponse cliente = new ClienteJsonResponse(cpf, "Erro",
                LocalDate.of(1999, 2, 2), LocalDateTime.now(), LocalDateTime.now(), endereco);

        PedidoRequestJson pedidoRequestJson = new PedidoRequestJson(cpf,List.of(new ItemPedidoRequestJson(sku, quantidade)),2, "123");

        when(produtoService.buscarPorSku(sku)).thenReturn(produto);
        when(clienteService.buscarClientesPorCpf(cpf)).thenReturn(cliente);
        when(pagamentoService.criarPagamento(any())).thenThrow(new RuntimeException("Falha no pagamento"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> useCase.criarPedido(pedidoRequestJson));

        assertTrue(ex.getMessage().contains("Erro ao processar pagamento"));
        verify(estoqueService).reporEstoque(sku, 1L);
    }
}
