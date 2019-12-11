package com.example.demo.rabbitmq.exchanges.defaultexchange;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {

    @Bean
    public Queue testDefaultExchangeQueue() {
        return new Queue("test.default.exchange.queue");
    }

}
