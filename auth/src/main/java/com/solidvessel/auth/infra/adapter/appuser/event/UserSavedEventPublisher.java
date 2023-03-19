package com.solidvessel.auth.infra.adapter.appuser.event;

import com.solidvessel.auth.domain.appuser.event.UserSavedEvent;
import com.solidvessel.auth.domain.appuser.port.UserSavedPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSavedEventPublisher implements UserSavedPort {

    private final RabbitTemplate rabbitTemplate;

    @Value("${exchanges.signUp}")
    private String signUpExchange;

    @Value("${routing-keys.user.saved}")
    private String userSavedRoutingKey;

    public void sendUserSavedEvent(final UserSavedEvent event) {
        rabbitTemplate.convertAndSend(signUpExchange, userSavedRoutingKey, event);
    }
}
