package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequest(
        @NotNull Long id,
        @NotNull String name,
        String description,
        @NotNull String brand,
        @NotNull Double price,
        @NotNull ProductCategory category,
        @NotNull ProductSubcategory subcategory,
        @NotNull Integer quantity
) {

    public UpdateProductCommand toCommand() {
        return new UpdateProductCommand(id, name, description, brand, price, category, subcategory, quantity);
    }
}
