package com.example.demo.rabbitmq.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AutoAckModeDelayRetryErrorHandler implements ErrorHandler {

    @Value("${rabbitmq.dead.letter.retry-max:3}")
    private Integer retryMaxCount;

    @Override
    public void handleError(Throwable throwable) {
        if (throwable instanceof ListenerExecutionFailedException) {
            Message failed = ((ListenerExecutionFailedException) throwable).getFailedMessage();
            if (failed != null) {
                long retryCount = getRetryCount(failed.getMessageProperties().getXDeathHeader());
                if (retryCount < retryMaxCount) {
                    throw new AmqpRejectAndDontRequeueException("nack and requeue is false, trigger dead-letter", throwable);
                } else {
                    //insert DB & ack
                    try {
                        String consumerQueue = failed.getMessageProperties().getConsumerQueue();
                        byte[] body = failed.getBody();
                        //TODO insert DB
                        System.out.println("consumerQueue : " + consumerQueue + ", body : " + body);
                    } catch (Exception e) {
                        throw new AmqpRejectAndDontRequeueException("nack and requeue is false, trigger dead-letter", throwable);
                    }
                    throw new ImmediateAcknowledgeAmqpException("acknowledge and message is move to database");
                }
            } else {
                log.error("message is null", throwable);
                throw new ImmediateAcknowledgeAmqpException("message is null, consume fail", throwable);
            }
        }

        log.error("unexpected exception", throwable);
        throw new ImmediateAcknowledgeAmqpException("unexpected exception, consume fail", throwable);
    }

    private long getRetryCount(List<Map<String, ?>> xDeath) {
        Long retryCount = 0L;
        if (xDeath != null && xDeath.size() > 0) {
            Map<String, ?> death = xDeath.get(0);
            retryCount = (Long) death.get("count");
        }
        return retryCount;
    }

}
