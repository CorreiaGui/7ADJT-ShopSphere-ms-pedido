package br.com.fiap.ms.pedidoservice.gateway.external.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqListenerConfiguration {

    public static final String QUEUE_NAME = "novo-pedido-queue";
    public static final String EXCHANGE_NAME = "pedido-exchange";
    public static final String ROUTING_KEY = "criar-pedido";

    @Bean
    public DirectExchange pedidoExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue pedidoQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding pedidoBinding(Queue pedidoQueue, DirectExchange pedidoExchange) {
        return BindingBuilder.bind(pedidoQueue).to(pedidoExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
