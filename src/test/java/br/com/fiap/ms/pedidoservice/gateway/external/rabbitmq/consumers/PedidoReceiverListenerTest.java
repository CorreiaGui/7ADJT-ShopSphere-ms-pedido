package br.com.fiap.ms.pedidoservice.gateway.external.rabbitmq.consumers;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.usecase.CriarPedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class PedidoReceiverListenerTest {

    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;

    @InjectMocks
    private PedidoReceiverListener listener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveChamarUseCaseAoReceberPedido() {
        PedidoRequestJson json = new PedidoRequestJson("33344455566",
                null, 1, "4444555566667777");

        listener.receberPedido(json);

        verify(criarPedidoUseCase, times(1)).criarPedido(json);
    }
}
