package com.example.demo.rabbitmq.exchanges.defaultexchange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "test.default.exchange.queue")
public class DefaultQueueConsumer {

    @RabbitHandler
    public void processMessage(String message) {
        log.info("DefaultQueueConsumer : " + message);
    }
}
