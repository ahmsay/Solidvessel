package com.solidvessel.payment.infra.adapter.cart.rest.request;

import com.solidvessel.payment.domain.cart.service.command.AddToCartCommand;
import com.solidvessel.shared.infra.util.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record AddToCartRequest(@NotNull Long productId, int quantity) {

    public AddToCartCommand toCommand() {
        return new AddToCartCommand(
                SessionUtil.getCurrentUserId(),
                productId,
                quantity
        );
    }
}
