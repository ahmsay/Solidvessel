package com.solidvessel.inventory.product.service.command;

public record AddProductToCartCommand(Long id, Integer quantity, String customerId) {
}
