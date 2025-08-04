package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.controller.json.PagamentoExternoRequest;
import br.com.fiap.ms.pedidoservice.domain.StatusPedido;
import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.ItemPedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.database.jpa.entity.PedidoEntity;
import br.com.fiap.ms.pedidoservice.gateway.external.estoque.service.EstoqueService;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.response.PagamentoResponse;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service.PagamentoService;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamentoExterno.service.PagamentoExternoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessarPagamentoExternoUseCaseTest {

    @Mock
    private PedidoGateway pedidoGateway;
    @Mock
    private EstoqueService estoqueService;
    @Mock
    private PagamentoService pagamentoService;
    @Mock
    private PagamentoExternoService pagamentoExternoService;

    @InjectMocks
    private ProcessarPagamentoExternoUseCase useCase;

    private UUID pedidoId;
    private UUID pagamentoId;
    private PagamentoExternoRequest request;
    private PedidoEntity pedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pedidoId = UUID.randomUUID();
        pagamentoId = UUID.randomUUID();
        request = new PagamentoExternoRequest(pagamentoId);

        pedido = PedidoEntity.builder()
                .id(pedidoId)
                .numeroPedido(123456)
                .status(StatusPedido.ABERTO)
                .pagamentoId(pagamentoId)
                .cpf("12345678901")
                .valorTotal(BigDecimal.TEN)
                .dataCriacao(LocalDateTime.now())
                .itens(List.of(
                        ItemPedidoEntity.builder().sku("SKU1").quantidade(2).build()
                ))
                .build();
    }

    @Test
    void deveProcessarPagamentoConfirmado() {
        when(pagamentoExternoService.obterStatusPagamento(request)).thenReturn("PAGAMENTO_CONFIRMADO");
        when(pagamentoService.buscarPorIdExterno(pagamentoId)).thenReturn(new PagamentoResponse(pagamentoId, pedidoId, null, null, null, null, null, null));
        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(pedido);

        String resultado = useCase.executar(request);

        assertTrue(resultado.contains("Pagamento confirmado com sucesso!"));
        verify(pedidoGateway).salvar(argThat(p -> p.getStatus() == StatusPedido.FECHADO_COM_SUCESSO));
    }

    @Test
    void deveProcessarPagamentoRejeitadoFaltaCreditoComEstorno() {
        when(pagamentoExternoService.obterStatusPagamento(request)).thenReturn("PAGAMENTO_REJEITADO_FALTA_CREDITO");
        when(pagamentoService.buscarPorIdExterno(pagamentoId)).thenReturn(new PagamentoResponse(pagamentoId, pedidoId, null, null, null, null, null, null));
        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(pedido);

        String resultado = useCase.executar(request);

        assertTrue(resultado.contains("Pagamento rejeitado por falta de crédito"));
        verify(estoqueService).reporEstoque("SKU1", 2L);
        verify(pedidoGateway).salvar(argThat(p -> p.getStatus() == StatusPedido.FECHADO_SEM_CREDITO));
    }

    @Test
    void deveProcessarPagamentoRejeitadoSemEstoque() {
        when(pagamentoExternoService.obterStatusPagamento(request)).thenReturn("PAGAMENTO_REJEITADO_SEM_ESTOQUE");
        when(pagamentoService.buscarPorIdExterno(pagamentoId)).thenReturn(new PagamentoResponse(pagamentoId, pedidoId, null, null, null, null, null, null));
        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(pedido);

        String resultado = useCase.executar(request);

        assertTrue(resultado.contains("Status do Pagamento: PAGAMENTO_REJEITADO_SEM_ESTOQUE"));
        verify(pedidoGateway).salvar(argThat(p -> p.getStatus() == StatusPedido.FECHADO_SEM_ESTOQUE));
    }

    @Test
    void deveIgnorarSePedidoJaFechado() {
        pedido.setStatus(StatusPedido.FECHADO_COM_SUCESSO);

        when(pagamentoExternoService.obterStatusPagamento(request)).thenReturn("PAGAMENTO_CONFIRMADO");
        when(pagamentoService.buscarPorIdExterno(pagamentoId)).thenReturn(new PagamentoResponse(pagamentoId, pedidoId, null, null, null, null, null, null));
        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(pedido);

        String resultado = useCase.executar(request);

        assertTrue(resultado.contains("se encontra FECHADO"));
        verify(pedidoGateway, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoParaStatusDesconhecido() {
        when(pagamentoExternoService.obterStatusPagamento(request)).thenReturn("STATUS_INVALIDO");
        when(pagamentoService.buscarPorIdExterno(pagamentoId)).thenReturn(new PagamentoResponse(pagamentoId, pedidoId, null, null, null, null, null, null));
        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(pedido);

        Exception e = assertThrows(RuntimeException.class, () -> useCase.executar(request));
        assertTrue(e.getCause() instanceof IllegalStateException);
    }

    @Test
    void deveLancarExcecaoSePagamentoNaoEncontrado() {
        when(pagamentoService.buscarPorIdExterno(pagamentoId)).thenReturn(null);

        Exception e = assertThrows(RuntimeException.class, () -> useCase.executar(request));
        assertTrue(e.getCause() instanceof IllegalArgumentException);
        assertTrue(e.getMessage().contains("Erro ao processar pagamento externo"));
    }

    @Test
    void deveLancarExcecaoSePedidoNaoEncontrado() {
        when(pagamentoService.buscarPorIdExterno(pagamentoId)).thenReturn(new PagamentoResponse(pagamentoId, pedidoId, null, null, null, null, null, null));
        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(null);

        Exception e = assertThrows(RuntimeException.class, () -> useCase.executar(request));
        assertTrue(e.getCause() instanceof IllegalArgumentException);
    }

    @Test
    void deveLancarRuntimeExceptionEmErroInesperado() {
        when(pagamentoExternoService.obterStatusPagamento(any())).thenThrow(new RuntimeException("Falha inesperada"));

        Exception e = assertThrows(RuntimeException.class, () -> useCase.executar(request));
        assertTrue(e.getMessage().contains("Erro ao processar pagamento externo"));
    }

    @Test
    void deveLogarErroAoEstornarItemComFalha() {
        when(pagamentoExternoService.obterStatusPagamento(request)).thenReturn("PAGAMENTO_REJEITADO_FALTA_CREDITO");
        when(pagamentoService.buscarPorIdExterno(pagamentoId)).thenReturn(new PagamentoResponse(pagamentoId, pedidoId, null, null, null, null, null, null));
        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(pedido);

        // simula erro no estoqueService
        doThrow(new RuntimeException("Falha no estoque")).when(estoqueService)
                .reporEstoque(anyString(), anyLong());

        String resultado = useCase.executar(request);

        assertTrue(resultado.contains("Pagamento rejeitado por falta de crédito"));
        verify(estoqueService).reporEstoque("SKU1", 2L);
        verify(pedidoGateway).salvar(argThat(p -> p.getStatus() == StatusPedido.FECHADO_SEM_CREDITO));
    }

}
