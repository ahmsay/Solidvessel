package com.microshop.auth.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.signUp}")
    private String signUpExchange;

    @Value("${routing-keys.user.saved}")
    private String userSavedRoutingKey;

    public EventDispatcher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUserSavedEvent(final UserSavedEvent event) {
        rabbitTemplate.convertAndSend(signUpExchange, userSavedRoutingKey, event);
    }
}
