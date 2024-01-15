package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.service.command.AddProductToCartCommand;
import com.solidvessel.shared.security.SessionUtil;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddProductToCartRequest(@NotNull @Min(1) Integer quantity) {

    public AddProductToCartCommand toCommand(Long id) {
        return new AddProductToCartCommand(id, quantity, SessionUtil.getCurrentUserId());
    }
}
