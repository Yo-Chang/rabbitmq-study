package com.example.rabbitmq.exchanges.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    public TopicExchange topicExchange01() {
        return new TopicExchange("topicExchange01");
    }

    @Bean
    public Queue topicQueue01() {
        return new Queue("topicQueue01");
    }

    @Bean
    public Queue topicQueue02() {
        return new Queue("topicQueue02");
    }

    @Bean
    public Binding bindingTopicQueue01(TopicExchange topicExchange01, Queue topicQueue01){
        return BindingBuilder.bind(topicQueue01).to(topicExchange01).with("topic.message");
    }

    @Bean
    public Binding bindingTopicQueue02(TopicExchange topicExchange01, Queue topicQueue02){
        return BindingBuilder.bind(topicQueue02).to(topicExchange01).with("topic.#");
    }
}
