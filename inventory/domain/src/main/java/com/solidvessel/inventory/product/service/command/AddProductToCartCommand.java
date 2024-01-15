package com.solidvessel.inventory.product.service.command;

public record AddProductToCartCommand(Long id, int quantity, String customerId) {
}
