package com.example.demo.rabbitmq.exchanges;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class RabbitExchangeController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{exchange}")
    public void sendDirect(@PathVariable String exchange, @RequestParam String key, @RequestParam String value, @RequestParam int quantity) {
        for (int i = 0; i < quantity; i++) {
            rabbitTemplate.convertAndSend(exchange, key, value);
        }
    }

    @PostMapping("/defaultExchange")
    public void sendDefaultExchange(@RequestParam String value, @RequestParam int quantity) {
        for (int i = 0; i < quantity; i++) {
            rabbitTemplate.convertAndSend("test.default.exchange.queue", value);
        }
    }
}
