package com.solidvessel.payment.adapter.in.product.event;

import com.solidvessel.payment.cart.service.AddToCartCommandService;
import com.solidvessel.payment.product.event.ProductAvailableEvent;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class ProductAvailableEventConsumerTest extends BaseUnitTest {

    @Mock
    private AddToCartCommandService addToCartCommandService;

    @Test
    void consumeAddToCartCommandServiceTest() {
        var event = new ProductAvailableEvent(1L, "chair", 10D, ProductCategory.FURNITURE, 3, "123");
        var eventConsumer = new ProductAvailableEventConsumer(addToCartCommandService);
        eventConsumer.consume(event);
        verify(addToCartCommandService).execute(event);
    }
}
