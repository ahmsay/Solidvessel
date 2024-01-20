package com.solidvessel.inventory.adapter.out.product.event;

import com.solidvessel.inventory.product.event.ProductAvailableEvent;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import static org.mockito.Mockito.verify;

public class ProductAvailableEventPublisherTest extends BaseUnitTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Value("${exchanges.product}")
    private String productExchange;

    @Value("${routing-keys.product.available}")
    private String productAvailableRoutingKey;

    @Test
    void publishProductAvailableEvent() {
        var event = new ProductAvailableEvent(1L, "macbook", 1200D, ProductCategory.ELECTRONICS, 3, "123");
        var eventPublisher = new ProductAvailableEventPublisher(rabbitTemplate);
        eventPublisher.publish(event);
        verify(rabbitTemplate).convertAndSend(productExchange, productAvailableRoutingKey, event);
    }
}
