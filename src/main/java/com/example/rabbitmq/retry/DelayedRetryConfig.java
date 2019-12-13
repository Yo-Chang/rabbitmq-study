package com.example.rabbitmq.retry;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayedRetryConfig {

    @Bean
    public DirectExchange directRetryExchange() {
        return new DirectExchange("directRetryExchange");
    }

    @Bean
    public Queue workQueue01() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", waitExchange01().getName());
        return new Queue("workQueue01", true, false, false, args);
    }

    @Bean
    public Binding bindingWorkQueue01(DirectExchange directRetryExchange, Queue workQueue01){
        return BindingBuilder.bind(workQueue01).to(directRetryExchange).with("workQueue01");
    }

    @Bean
    public DirectExchange waitExchange01() {
        return new DirectExchange("waitExchange01");
    }

    @Bean
    public Queue waitQueue01() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", directRetryExchange().getName());
        args.put("x-message-ttl", 10000);
        return new Queue("waitQueue01", true, false, false, args);
    }

    @Bean
    public Binding bindingWaitQueue01(DirectExchange waitExchange01, Queue waitQueue01){
        return BindingBuilder.bind(waitQueue01).to(waitExchange01).with("workQueue01");
    }
}
