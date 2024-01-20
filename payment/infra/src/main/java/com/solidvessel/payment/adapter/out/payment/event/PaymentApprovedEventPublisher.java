package com.solidvessel.payment.adapter.out.payment.event;

import com.solidvessel.payment.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentApprovedEventPublisher implements EventPublisher<PaymentApprovedEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.payment}")
    private String paymentExchange;

    @Value("${routing-keys.payment.approved}")
    private String paymentApprovedRoutingKey;

    @Override
    public void publish(PaymentApprovedEvent event) {
        rabbitTemplate.convertAndSend(paymentExchange, paymentApprovedRoutingKey, event);
    }
}
