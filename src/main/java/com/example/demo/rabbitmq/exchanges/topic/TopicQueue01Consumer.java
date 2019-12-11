package com.example.demo.rabbitmq.exchanges.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "#{topicQueue01.name}")
public class TopicQueue01Consumer {

    @RabbitHandler
    public void processMessage(String message) {
        log.info("TopicQueue01Consumer : " + message);
    }


}
