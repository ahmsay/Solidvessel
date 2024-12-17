package com.solidvessel.account.adapter.out.address.event;

import com.solidvessel.account.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.shared.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrimaryAddressSavedEventPublisher implements EventPublisher<PrimaryAddressSavedEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.address}")
    private String addressExchangeName;

    @Value("${routing-keys.address.primary-saved}")
    private String primaryAddressSavedRoutingKey;

    @Override
    public void publish(PrimaryAddressSavedEvent event) {
        rabbitTemplate.convertAndSend(addressExchangeName, primaryAddressSavedRoutingKey, event);
    }
}
