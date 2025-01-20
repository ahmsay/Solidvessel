package com.solidvessel.order.adapter.in.payment.event;

import com.solidvessel.order.order.service.AddOrderCommandService;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class PaymentApprovedEventConsumerTest extends BaseUnitTest {

    @Mock
    private AddOrderCommandService addOrderCommandService;

    @Test
    void consumePaymentApprovedEvent() {
        var event = new PaymentApprovedEvent(1L, "123", "4913 baku, azerbaijan");
        var eventConsumer = new PaymentApprovedEventConsumer(addOrderCommandService);
        eventConsumer.consume(event);
        verify(addOrderCommandService).execute(event);
    }
}
