package br.com.fiap.ms.pedidoservice.gateway.external.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import static org.junit.jupiter.api.Assertions.*;

class RabbitMqListenerConfigurationTest {

    private RabbitMqListenerConfiguration config;

    @BeforeEach
    void setUp() {
        config = new RabbitMqListenerConfiguration();
    }

    @Test
    void deveCriarExchangeCorretamente() {
        DirectExchange exchange = config.pedidoExchange();

        assertNotNull(exchange);
        assertEquals(RabbitMqListenerConfiguration.EXCHANGE_NAME, exchange.getName());
    }

    @Test
    void deveCriarQueueCorretamente() {
        Queue queue = config.pedidoQueue();

        assertNotNull(queue);
        assertEquals(RabbitMqListenerConfiguration.QUEUE_NAME, queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void deveCriarBindingCorretamente() {
        Queue queue = config.pedidoQueue();
        DirectExchange exchange = config.pedidoExchange();

        Binding binding = config.pedidoBinding(queue, exchange);

        assertNotNull(binding);
        assertEquals(RabbitMqListenerConfiguration.ROUTING_KEY, binding.getRoutingKey());
        assertEquals(queue.getName(), binding.getDestination());
        assertEquals(exchange.getName(), binding.getExchange());
    }

    @Test
    void deveCriarMessageConverterJackson() {
        MessageConverter converter = config.jsonConverter();

        assertNotNull(converter);
        assertTrue(converter instanceof Jackson2JsonMessageConverter);
    }
}
