package com.solidvessel.payment.adapter.out.payment.event;

import com.solidvessel.payment.payment.event.PaymentSavedEvent;
import com.solidvessel.shared.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSavedEventPublisher implements EventPublisher<PaymentSavedEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.payment}")
    private String paymentExchange;

    @Value("${routing-keys.payment.saved}")
    private String paymentSavedRoutingKey;

    @Override
    public void publish(PaymentSavedEvent event) {
        rabbitTemplate.convertAndSend(paymentExchange, paymentSavedRoutingKey, event);
    }
}
