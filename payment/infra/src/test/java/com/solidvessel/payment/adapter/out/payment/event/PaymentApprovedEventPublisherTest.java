package com.solidvessel.payment.adapter.out.payment.event;

import com.solidvessel.payment.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import static org.mockito.Mockito.verify;

public class PaymentApprovedEventPublisherTest extends BaseUnitTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Value("${exchanges.payment}")
    private String paymentExchange;

    @Value("${routing-keys.payment.approved}")
    private String paymentApprovedRoutingKey;

    @Test
    void publishPaymentApprovedEvent() {
        var event = new PaymentApprovedEvent(1l, "123", "48249 helsinki, finland");
        var eventPublisher = new PaymentApprovedEventPublisher(rabbitTemplate);
        eventPublisher.publish(event);
        verify(rabbitTemplate).convertAndSend(paymentExchange, paymentApprovedRoutingKey, event);
    }
}
