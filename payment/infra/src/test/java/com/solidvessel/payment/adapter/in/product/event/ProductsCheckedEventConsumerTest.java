package com.solidvessel.payment.adapter.in.product.event;

import com.solidvessel.payment.payment.service.UpdatePaymentStatusCommandService;
import com.solidvessel.payment.product.event.ProductsCheckedEvent;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class ProductsCheckedEventConsumerTest extends BaseUnitTest {

    @Mock
    private UpdatePaymentStatusCommandService updatePaymentStatusCommandService;

    @Test
    void consumeProductsCheckedEvent() {
        var event = new ProductsCheckedEvent(1L, false, "123");
        var eventConsumer = new ProductsCheckedEventConsumer(updatePaymentStatusCommandService);
        eventConsumer.consume(event);
        verify(updatePaymentStatusCommandService).execute(event);
    }
}
