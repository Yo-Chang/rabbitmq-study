package com.example.rabbitmq.exchanges.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "#{fanoutQueue01.name}")
public class FanoutQueue01Consumer {

    @RabbitHandler
    public void processMessage(String message) {
        log.info("FanoutQueue01Consumer01 : " + message);
    }
}
