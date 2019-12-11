package com.example.demo.rabbitmq.exchanges.fanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange01() {
        return new FanoutExchange("fanoutExchange01");
    }

    @Bean
    public Queue fanoutQueue01() {
        return new Queue("fanoutQueue01");
    }

    @Bean
    public Queue fanoutQueue02() {
        return new Queue("fanoutQueue02");
    }

    @Bean
    public Binding bindingFanoutQueue01(FanoutExchange fanoutExchange01, Queue fanoutQueue01){
        return BindingBuilder.bind(fanoutQueue01).to(fanoutExchange01);
    }

    @Bean
    public Binding bindingFanoutQueue02(FanoutExchange fanoutExchange01, Queue fanoutQueue02){
        return BindingBuilder.bind(fanoutQueue02).to(fanoutExchange01);
    }
}
