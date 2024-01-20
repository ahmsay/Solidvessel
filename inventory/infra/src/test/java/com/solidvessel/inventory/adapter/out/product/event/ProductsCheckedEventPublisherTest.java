package com.solidvessel.inventory.adapter.out.product.event;

import com.solidvessel.inventory.product.event.ProductsCheckedEvent;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import static org.mockito.Mockito.verify;

public class ProductsCheckedEventPublisherTest extends BaseUnitTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Value("${exchanges.product}")
    private String productExchange;

    @Value("${routing-keys.product.checked}")
    private String productCheckedRoutingKey;

    @Test
    void publishProductsCheckedEvent() {
        var event = new ProductsCheckedEvent(1L, true, "123");
        var eventPublisher = new ProductsCheckedEventPublisher(rabbitTemplate);
        eventPublisher.publish(event);
        verify(rabbitTemplate).convertAndSend(productExchange, productCheckedRoutingKey, event);
    }
}
