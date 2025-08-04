package br.com.fiap.ms.pedidoservice.gateway.external.pagamentoExterno.service;

import br.com.fiap.ms.pedidoservice.controller.json.PagamentoExternoRequest;
import br.com.fiap.ms.pedidoservice.gateway.external.pagamentoExterno.PagamentoExternoFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PagamentoExternoServiceTest {

    @Mock
    private PagamentoExternoFeignClient pagamentoExternoFeignClient;

    private PagamentoExternoService pagamentoExternoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagamentoExternoService = new PagamentoExternoService(pagamentoExternoFeignClient);
    }

    @Test
    void testObterStatusPagamento_RetornaStatusValido() {
        PagamentoExternoRequest request = new PagamentoExternoRequest(UUID.randomUUID());

        String status = pagamentoExternoService.obterStatusPagamento(request);

        assertTrue(
                status.equals("PAGAMENTO_CONFIRMADO") || status.equals("PAGAMENTO_REJEITADO_FALTA_CREDITO"),
                "O status retornado deve ser um dos valores esperados"
        );
    }
}
