package com.example.rabbitmq.exchanges.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "#{directQueue01.name}")
public class DirectQueue01Consumer02 {

    @RabbitHandler
    public void processMessage(String message) {
        log.info("DirectQueue01Consumer02 : " + message);
    }


}
