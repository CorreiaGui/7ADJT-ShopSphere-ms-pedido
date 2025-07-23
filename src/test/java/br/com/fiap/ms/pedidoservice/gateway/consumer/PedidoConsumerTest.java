package br.com.fiap.ms.pedidoservice.gateway.consumer;

import br.com.fiap.ms.pedidoservice.domain.ItemPedido;
import br.com.fiap.ms.pedidoservice.domain.Pedido;
import br.com.fiap.ms.pedidoservice.usecase.ProcessarPedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PedidoConsumerTest {

    private ProcessarPedidoUseCase useCase;
    private PedidoConsumer pedidoConsumer;

    @BeforeEach
    void setUp() {
        useCase = mock(ProcessarPedidoUseCase.class);
        pedidoConsumer = new PedidoConsumer(useCase);
    }

    @Test
    void deveConsumirPedidoComSucesso() {
        // Arrange
        ItemPedido item1 = new ItemPedido("SKU123", 1);
        ItemPedido item2 = new ItemPedido("SKU456", 2);

        Pedido pedido = new Pedido();
        pedido.setIdCliente("456");
        pedido.setNumeroCartao("1234123412341234");
        pedido.setItens(Arrays.asList(item1, item2));

        // Act
        pedidoConsumer.consumirPedido(pedido);

        // Assert
        verify(useCase, times(1)).processar(pedido);
    }

    @Test
    void deveLancarAmqpRejectExceptionQuandoFalhar() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setIdCliente("999");

        doThrow(new RuntimeException("Erro ao processar")).when(useCase).processar(pedido);

        // Act & Assert
        assertThrows(AmqpRejectAndDontRequeueException.class, () -> {
            pedidoConsumer.consumirPedido(pedido);
        });

        verify(useCase, times(1)).processar(pedido);
    }
}
