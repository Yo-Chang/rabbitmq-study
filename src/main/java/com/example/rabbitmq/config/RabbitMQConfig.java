package com.example.rabbitmq.config;

import com.example.rabbitmq.retry.AutoAckModeDelayRetryErrorHandler;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.DirectRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    AutoAckModeDelayRetryErrorHandler errorHandler;

    /**
     * Producer
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    /**
     * Consumer
     */
    @Bean
    public DirectRabbitListenerContainerFactory rabbitListenerContainerFactory(
            DirectRabbitListenerContainerFactoryConfigurer configure,
            ConnectionFactory connectionFactory) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setErrorHandler(errorHandler);
        configure.configure(factory, connectionFactory);
        return factory;
    }
}
