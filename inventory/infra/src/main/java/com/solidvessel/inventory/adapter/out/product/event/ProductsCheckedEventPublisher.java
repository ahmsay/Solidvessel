package com.solidvessel.inventory.adapter.out.product.event;

import com.solidvessel.inventory.product.event.ProductsCheckedEvent;
import com.solidvessel.shared.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsCheckedEventPublisher implements EventPublisher<ProductsCheckedEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.product}")
    private String productExchange;

    @Value("${routing-keys.product.checked}")
    private String productCheckedRoutingKey;

    @Override
    public void publish(ProductsCheckedEvent event) {
        rabbitTemplate.convertAndSend(productExchange, productCheckedRoutingKey, event);
    }
}
