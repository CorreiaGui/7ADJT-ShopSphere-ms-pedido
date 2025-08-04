package br.com.fiap.ms.pedidoservice.controller;

import br.com.fiap.ms.pedidoservice.controller.json.PagamentoExternoRequest;
import br.com.fiap.ms.pedidoservice.usecase.ProcessarPagamentoExternoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoPagamentoControllerTest {

    @Mock
    private ProcessarPagamentoExternoUseCase processarPagamentoExternoUseCase;

    @InjectMocks
    private PedidoPagamentoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveProcessarPagamentoExternoComSucesso() {
        PagamentoExternoRequest request = new PagamentoExternoRequest(UUID.randomUUID());
        when(processarPagamentoExternoUseCase.executar(request)).thenReturn("Pagamento confirmado com sucesso!");

        ResponseEntity<String> response = controller.processarPagamentoExterno(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Pagamento confirmado com sucesso!", response.getBody());
        verify(processarPagamentoExternoUseCase, times(1)).executar(request);
    }
}
