package com.solidvessel.inventory.infra.adapter.product.rest.request;

import com.solidvessel.inventory.domain.product.service.command.AddProductCommand;

public record AddProductRequest(String name, Double price, String category) {

    public AddProductCommand toCommand() {
        return new AddProductCommand(name, price, category);
    }
}
