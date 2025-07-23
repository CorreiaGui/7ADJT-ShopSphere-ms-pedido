package br.com.fiap.ms.pedidoservice.gateway.consumer;

import br.com.fiap.ms.pedidoservice.domain.Pedido;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Consumidor de mensagens de pedidos.
 * Escuta a fila "novo-pedido-queue" e processa os pedidos recebidos.
 */
@Component
public class PedidoConsumer {

    private final ProcessarPedidoUseCase useCase;

    public PedidoConsumer(ProcessarPedidoUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = "novo-pedido-queue")
    public void consumirPedido(Pedido pedido) {
        try {
            System.out.println("Pedido recebido do RabbitMQ: " + pedido);
            useCase.processar(pedido);
        } catch (Exception e) {
            System.err.println("Erro ao processar pedido: " + e.getMessage());
            e.printStackTrace();

            // Lança exceção fatal para não reprocessar a mensagem infinitamente
            throw new AmqpRejectAndDontRequeueException("Erro ao processar pedido", e);
        }
    }


}
