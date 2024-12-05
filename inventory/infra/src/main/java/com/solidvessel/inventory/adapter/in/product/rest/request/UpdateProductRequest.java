package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequest(
        @NotNull Long id,
        @NotNull String name,
        @NotNull Double price,
        @NotNull ProductCategory category,
        @NotNull Integer quantity
) {

    public UpdateProductCommand toCommand() {
        return new UpdateProductCommand(id, name, price, category, quantity);
    }
}
