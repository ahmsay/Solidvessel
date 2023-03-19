package com.solidvessel.payment.infra.adapter.payment.event;

import com.solidvessel.payment.domain.payment.event.PaymentSavedEvent;
import com.solidvessel.payment.domain.payment.port.PaymentSavedPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSavedEventPublisher implements PaymentSavedPort {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.payment}")
    private String paymentExchange;

    @Value("${routing-keys.payment.saved}")
    private String paymentSavedRoutingKey;

    public void sendPaymentSavedEvent(final PaymentSavedEvent event) {
        rabbitTemplate.convertAndSend(paymentExchange, paymentSavedRoutingKey, event);
    }
}
