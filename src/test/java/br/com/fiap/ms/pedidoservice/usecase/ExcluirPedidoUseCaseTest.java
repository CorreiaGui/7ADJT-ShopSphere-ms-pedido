package br.com.fiap.ms.pedidoservice.usecase;

import br.com.fiap.ms.pedidoservice.gateway.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;

class ExcluirPedidoUseCaseTest {

    @Mock
    private PedidoGateway gateway;

    @InjectMocks
    private ExcluirPedidoUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveExcluirPedidoComSucesso() {
        // given
        UUID id = UUID.randomUUID();

        // when
        useCase.excluirPedido(id);

        // then
        verify(gateway, times(1)).excluirPedido(id);
    }
}
