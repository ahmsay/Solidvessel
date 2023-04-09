package com.solidvessel.payment.domain.appuser.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solidvessel.payment.domain.cart.service.command.CreateCartCommand;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserSavedEvent(Long userId) {

    public CreateCartCommand toCommand() {
        return new CreateCartCommand(userId);
    }
}
