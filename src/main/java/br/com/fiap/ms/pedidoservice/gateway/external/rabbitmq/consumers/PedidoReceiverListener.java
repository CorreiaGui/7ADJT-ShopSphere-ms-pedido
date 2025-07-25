package br.com.fiap.ms.pedidoservice.gateway.external.rabbitmq.consumers;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Listener para receber pedidos da fila RabbitMQ.
 * Processa os pedidos recebidos utilizando o caso de uso PedidoReceiverUseCase.
 */
@Component
@RequiredArgsConstructor
public class PedidoReceiverListener {

    @RabbitListener(queues = "novo-pedido-queue")
    public void receberPedido() {
        System.out.println("Pedido recebido: ");
    }
}