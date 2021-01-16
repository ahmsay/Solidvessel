package com.microshop.paymentservice.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher implements IEventDispatcher {

    private final RabbitTemplate rabbitTemplate;
    private final String paymentExchange;
    private final String paymentSavedRoutingKey;

    public EventDispatcher(final RabbitTemplate rabbitTemplate,
                           @Value("${payment.exchange}") final String paymentExchange,
                           @Value("${payment.saved.key}") final String paymentSavedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.paymentExchange = paymentExchange;
        this.paymentSavedRoutingKey = paymentSavedRoutingKey;
    }

    @Override
    public void send(final PaymentSavedEvent event) {
        rabbitTemplate.convertAndSend(paymentExchange, paymentSavedRoutingKey, event);
    }
}
