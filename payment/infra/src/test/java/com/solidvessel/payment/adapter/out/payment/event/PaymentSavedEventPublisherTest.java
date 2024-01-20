package com.solidvessel.payment.adapter.out.payment.event;

import com.solidvessel.payment.payment.event.PaymentSavedEvent;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

import static org.mockito.Mockito.verify;

public class PaymentSavedEventPublisherTest extends BaseUnitTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Value("${exchanges.payment}")
    private String paymentExchange;

    @Value("${routing-keys.payment.saved}")
    private String paymentSavedRoutingKey;

    @Test
    void publishPaymentSavedEvent() {
        var event = new PaymentSavedEvent(1L, "123", Map.of(3L, 2));
        var eventPublisher = new PaymentSavedEventPublisher(rabbitTemplate);
        eventPublisher.publish(event);
        verify(rabbitTemplate).convertAndSend(paymentExchange, paymentSavedRoutingKey, event);
    }
}
