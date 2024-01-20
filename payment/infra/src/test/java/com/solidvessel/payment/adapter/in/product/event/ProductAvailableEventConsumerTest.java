package com.solidvessel.payment.adapter.in.product.event;

import com.solidvessel.payment.cart.service.AddToCartCommandService;
import com.solidvessel.payment.product.event.ProductAvailableEvent;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class ProductAvailableEventConsumerTest extends BaseUnitTest {

    @Mock
    private AddToCartCommandService addToCartCommandService;

    @Test
    void consumeAddToCartCommandServiceTest() {
        var event = new ProductAvailableEvent(1L, "chair", 10D, ProductCategory.FURNITURE, 3, "123");
        var eventConsumer = new ProductAvailableEventConsumer(addToCartCommandService);
        eventConsumer.consume(event);
        var command = event.toCommand();
        assertEquals(command.productId(), event.id());
        assertEquals(command.name(), event.name());
        assertEquals(command.price(), event.price());
        assertEquals(command.category(), event.productCategory());
        assertEquals(command.quantity(), event.desiredQuantity());
        assertEquals(command.customerId(), event.customerId());
        verify(addToCartCommandService).execute(command);
    }
}
