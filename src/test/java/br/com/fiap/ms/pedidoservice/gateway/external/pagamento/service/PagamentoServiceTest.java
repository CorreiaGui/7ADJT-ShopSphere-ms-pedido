package br.com.fiap.ms.pedidoservice.gateway.external.pagamento.service;

import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.PagamentoFeignClient;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.request.PagamentoRequest;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamento.response.PagamentoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    @Mock
    private PagamentoFeignClient pagamentoFeignClient;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarPagamento_deveDelegarParaFeignClientERetornarString() {
        PagamentoRequest request = new PagamentoRequest(UUID.randomUUID(),
                1,
                "1234123412341234",
                new BigDecimal("100.00"));
        String retornoEsperado = "Pagamento criado com sucesso";

        when(pagamentoFeignClient.criarPagamento(request)).thenReturn(retornoEsperado);

        String resultado = pagamentoService.criarPagamento(request);

        verify(pagamentoFeignClient, times(1)).criarPagamento(request);
        assertEquals(retornoEsperado, resultado);
    }

    @Test
    void testBuscarPorIdExterno() {
        UUID id = UUID.randomUUID();
        PagamentoResponse expectedResponse = new PagamentoResponse(UUID.randomUUID(),
                UUID.randomUUID(),
                1,
                "1234123412341234",
                new BigDecimal("100.00"),
                "222",
                LocalDateTime.now(),
                null);

        when(pagamentoFeignClient.buscarPorIdExterno(any(UUID.class))).thenReturn(expectedResponse);

        PagamentoResponse actualResponse = pagamentoService.buscarPorIdExterno(id);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(pagamentoFeignClient, times(1)).buscarPorIdExterno(id);
    }
}
