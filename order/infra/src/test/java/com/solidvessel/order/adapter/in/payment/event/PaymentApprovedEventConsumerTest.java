package com.solidvessel.order.adapter.in.payment.event;

import com.solidvessel.order.order.service.AddOrderCommandService;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class PaymentApprovedEventConsumerTest extends BaseUnitTest {

    @Mock
    private AddOrderCommandService addOrderCommandService;

    @Test
    void consumePaymentApprovedEvent() {
        var event = new PaymentApprovedEvent(1L, "123");
        var eventConsumer = new PaymentApprovedEventConsumer(addOrderCommandService);
        eventConsumer.consume(event);
        var command = event.toCommand();
        assertEquals(command.paymentId(), event.paymentId());
        assertEquals(command.customerId(), event.customerId());
        verify(addOrderCommandService).execute(command);
    }
}
