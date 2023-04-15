package com.solidvessel.inventory.domain.product.service.command;

public record AddProductCommand(String name, Double price, String category) {
}
