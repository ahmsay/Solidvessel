package com.solidvessel.payment.infra.adapter.payment.rest.request;

import com.solidvessel.payment.domain.payment.service.command.AddToCartCommand;
import com.solidvessel.shared.infra.util.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record AddToCartRequest(@NotNull Long productId) {

    public AddToCartCommand toCommand() {
        return new AddToCartCommand(
                SessionUtil.getCurrentUserId(),
                productId
        );
    }
}
