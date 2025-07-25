package br.com.fiap.ms.pedidoservice.gateway.external.rabbitmq.consumers;

import org.junit.jupiter.api.Test;

class PedidoReceiverListenerTest {

    @Test
    void receberPedido_naoDeveLancarExcecao() {
        PedidoReceiverListener listener = new PedidoReceiverListener();
        listener.receberPedido();
    }
}

