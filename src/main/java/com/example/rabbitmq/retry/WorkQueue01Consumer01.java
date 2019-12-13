package com.example.rabbitmq.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "#{workQueue01.name}")
public class WorkQueue01Consumer01 {

    @RabbitHandler
    public void processMessage(String message) {
        log.info("WorkQueue01Consumer01 : " + message);
        Integer value = Integer.valueOf(message);
    }

}
