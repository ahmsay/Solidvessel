package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequest(
        @NotNull Long id,
        @NotNull String name,
        @NotNull Double price,
        @NotNull ProductCategory category,
        ProductSubcategory subcategory,
        @NotNull Integer quantity
) {

    public UpdateProductRequest(Long id, String name, Double price, ProductCategory category, Integer quantity) {
        this(id, name, price, category, null, quantity);
    }

    public UpdateProductCommand toCommand() {
        return new UpdateProductCommand(id, name, price, category, subcategory, quantity);
    }
}
