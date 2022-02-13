package com.microshop.payment.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.payment}")
    private String paymentExchange;

    @Value("${routing-keys.payment.saved}")
    private String paymentSavedRoutingKey;

    public EventDispatcher(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPaymentSavedEvent(final PaymentSavedEvent event) {
        rabbitTemplate.convertAndSend(paymentExchange, paymentSavedRoutingKey, event);
    }
}
