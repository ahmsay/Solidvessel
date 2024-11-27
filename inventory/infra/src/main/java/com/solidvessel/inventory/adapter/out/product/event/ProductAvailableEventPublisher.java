package com.solidvessel.inventory.adapter.out.product.event;

import com.solidvessel.inventory.product.event.ProductAvailableEvent;
import com.solidvessel.shared.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAvailableEventPublisher implements EventPublisher<ProductAvailableEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.product}")
    private String productExchange;

    @Value("${routing-keys.product.available}")
    private String productAvailableRoutingKey;

    @Override
    public void publish(ProductAvailableEvent event) {
        rabbitTemplate.convertAndSend(productExchange, productAvailableRoutingKey, event);
    }
}
