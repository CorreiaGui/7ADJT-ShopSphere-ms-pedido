package br.com.fiap.ms.pedidoservice.gateway.external.rabbitmq.consumers;

import br.com.fiap.ms.pedidoservice.controller.json.PedidoRequestJson;
import br.com.fiap.ms.pedidoservice.usecase.CriarPedidoUseCase;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Listener para receber pedidos da fila RabbitMQ.
 * Processa os pedidos recebidos utilizando o caso de uso PedidoReceiverUseCase.
 */
@Slf4j
@Component
public class PedidoReceiverListener {

    @Autowired
    private CriarPedidoUseCase usecase;

    @RabbitListener(queues = "novo-pedido-queue")
    public void receberPedido(PedidoRequestJson json) {
        try {
            usecase.criarPedido(json);
        } catch (FeignException.BadRequest e) {
            log.warn("Erro de regra de negócio: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado", e);
            throw e;
        }
    }
}