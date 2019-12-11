package com.example.demo.rabbitmq.exchanges.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "#{directQueue01.name}")
public class DirectQueue01Consumer01 {

    @RabbitHandler
    public void processMessage(String message) {
        log.info("DirectQueue01Consumer01 : " + message);
    }


}
