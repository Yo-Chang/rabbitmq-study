package com.example.rabbitmq.exchanges.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "#{fanoutQueue02.name}")
public class FanoutQueue02Consumer {

    @RabbitHandler
    public void processMessage(String message) {
        log.info("FanoutQueue02Consumer : " + message);
    }
}
