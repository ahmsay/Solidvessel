package com.solidvessel.payment.adapter.in.cart.rest.request;

import com.solidvessel.payment.cart.service.command.AddToCartCommand;
import com.solidvessel.shared.infra.security.SessionUtil;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddToCartRequest(@NotNull Long productId, @NotNull @Min(1) Integer quantity) {

    public AddToCartCommand toCommand() {
        return new AddToCartCommand(
                SessionUtil.getCurrentUserId(),
                productId,
                quantity
        );
    }
}
