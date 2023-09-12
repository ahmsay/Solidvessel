package com.solidvessel.payment.infra.adapter.in.cart.rest.request;

import com.solidvessel.payment.domain.cart.service.command.RemoveFromCartCommand;
import com.solidvessel.shared.infra.security.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record RemoveFromCartRequest(@NotNull Long productId) {

    public RemoveFromCartCommand toCommand() {
        return new RemoveFromCartCommand(
                SessionUtil.getCurrentUserId(),
                productId
        );
    }
}
