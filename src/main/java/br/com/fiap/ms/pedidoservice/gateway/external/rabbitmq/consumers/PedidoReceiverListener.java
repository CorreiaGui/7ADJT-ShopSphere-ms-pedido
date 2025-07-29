package br.com.fiap.ms.pedidoservice.gateway.external.rabbitmq.consumers;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.usecase.CriarPedidoUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Listener para receber pedidos da fila RabbitMQ.
 * Processa os pedidos recebidos utilizando o caso de uso PedidoReceiverUseCase.
 */
@Component
public class PedidoReceiverListener {

    @Autowired
    private CriarPedidoUseCase usecase;

    @RabbitListener(queues = "novo-pedido-queue")
    public void receberPedido(PedidoRequestJson json) {
        usecase.criarPedido(json);
    }
}