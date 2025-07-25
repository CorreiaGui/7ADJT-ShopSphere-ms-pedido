package br.com.fiap.ms.pedidoservice.gateway.external.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do RabbitMQ para converter mensagens em JSON.
 * Utiliza Jackson2JsonMessageConverter para serialização e desserialização de mensagens.
 */
@Configuration
public class RabbitMqListenerConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue pedidoQueue() {
        return new Queue("novo-pedido-queue", true);
    }
}
