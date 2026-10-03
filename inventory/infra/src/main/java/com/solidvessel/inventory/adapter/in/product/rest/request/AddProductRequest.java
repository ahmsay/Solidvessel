package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import com.solidvessel.inventory.product.service.command.AddProductCommand;
import jakarta.validation.constraints.NotNull;

public record AddProductRequest(
        @NotNull String name, String description, @NotNull Double price, @NotNull ProductCategory category,
        @NotNull ProductSubcategory subcategory, @NotNull Integer quantity
) {

    public AddProductCommand toCommand() {
        return new AddProductCommand(name, description, price, category, subcategory, quantity);
    }
}
