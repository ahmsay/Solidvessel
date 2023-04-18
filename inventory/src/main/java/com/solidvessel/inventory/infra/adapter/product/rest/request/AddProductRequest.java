package com.solidvessel.inventory.infra.adapter.product.rest.request;

import com.solidvessel.inventory.domain.product.model.ProductCategory;
import com.solidvessel.inventory.domain.product.service.command.AddProductCommand;

public record AddProductRequest(String name, Double price, ProductCategory category) {

    public AddProductCommand toCommand() {
        return new AddProductCommand(name, price, category);
    }
}
