package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.service.command.AddProductCommand;
import jakarta.validation.constraints.NotNull;

public record AddProductRequest(
        @NotNull String name, @NotNull Double price, @NotNull ProductCategory category, @NotNull Integer quantity
) {

    public AddProductCommand toCommand() {
        return new AddProductCommand(name, price, category, quantity);
    }
}
