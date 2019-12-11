package com.example.demo.rabbitmq.exchanges.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "#{topicQueue02.name}")
public class TopicQueue02Consumer {

    @RabbitHandler
    public void processMessage(String message) {
        log.info("TopicQueue02Consumer : " + message);
    }


}
