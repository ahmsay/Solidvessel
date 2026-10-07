package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import com.solidvessel.inventory.product.service.command.AddProductCommand;
import jakarta.validation.constraints.NotNull;

public record AddProductRequest(
        @NotNull String name, String description, String brand, @NotNull Double price,
        @NotNull ProductCategory category,
        @NotNull ProductSubcategory subcategory, @NotNull Integer quantity
) {

    public AddProductRequest(String name, String description, Double price, ProductCategory category,
                             ProductSubcategory subcategory, Integer quantity) {
        this(name, description, null, price, category, subcategory, quantity);
    }

    public AddProductCommand toCommand() {
        return new AddProductCommand(name, description, brand, price, category, subcategory, quantity);
    }
}
