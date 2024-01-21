package com.solidvessel.inventory.adapter.in.payment.event;

import com.solidvessel.inventory.payment.event.PaymentSavedEvent;
import com.solidvessel.inventory.product.service.UpdateProductQuantitiesCommandService;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.Mockito.verify;

public class PaymentSavedEventConsumerTest extends BaseUnitTest {

    @Mock
    private UpdateProductQuantitiesCommandService updateProductQuantitiesCommandService;

    @Test
    void consumePaymentSavedEventConsumer() {
        var event = new PaymentSavedEvent(1L, "123", Map.of(4L, 2));
        var eventConsumer = new PaymentSavedEventConsumer(updateProductQuantitiesCommandService);
        eventConsumer.consume(event);
        verify(updateProductQuantitiesCommandService).execute(event);
    }
}
