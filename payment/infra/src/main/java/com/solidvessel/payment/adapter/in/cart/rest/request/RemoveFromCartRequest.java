package com.solidvessel.payment.adapter.in.cart.rest.request;

import com.solidvessel.payment.cart.service.command.RemoveFromCartCommand;
import com.solidvessel.shared.security.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record RemoveFromCartRequest(@NotNull Long productId) {

    public RemoveFromCartCommand toCommand() {
        return new RemoveFromCartCommand(
                SessionUtil.getCurrentUserId(),
                productId
        );
    }
}
