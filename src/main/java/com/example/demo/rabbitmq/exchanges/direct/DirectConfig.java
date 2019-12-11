package com.example.demo.rabbitmq.exchanges.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    @Bean
    public DirectExchange directExchange01() {
        return new DirectExchange("directExchange01");
    }

    @Bean
    public Queue directQueue01() {
        return new Queue("directQueue01");
    }

    @Bean
    public Binding bindingDirectQueue01(DirectExchange directExchange01, Queue directQueue01){
        return BindingBuilder.bind(directQueue01).to(directExchange01).with("directQueue01");
    }

}
