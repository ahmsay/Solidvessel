package com.solidvessel.inventory.infra.adapter.product.rest.request;

import com.solidvessel.inventory.domain.product.model.ProductCategory;
import com.solidvessel.inventory.domain.product.service.command.AddProductCommand;
import jakarta.validation.constraints.NotNull;

public record AddProductRequest(
        @NotNull String name, @NotNull Double price, @NotNull ProductCategory category, @NotNull int quantity
) {

    public AddProductCommand toCommand() {
        return new AddProductCommand(name, price, category, quantity);
    }
}
