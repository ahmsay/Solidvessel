package com.solidvessel.inventory.adapter.out.product.event;

import com.solidvessel.inventory.product.event.InventoryCheckedEvent;
import com.solidvessel.shared.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryCheckedEventPublisher implements EventPublisher<InventoryCheckedEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.inventory}")
    private String inventoryExchange;

    @Value("${routing-keys.inventory.checked}")
    private String inventoryCheckedRoutingKey;

    @Override
    public void publish(InventoryCheckedEvent event) {
        rabbitTemplate.convertAndSend(inventoryExchange, inventoryCheckedRoutingKey, event);
    }
}
