package com.solidvessel.payment.cart.service.command;

public record AddToCartCommand(String customerId, Long productId, int quantity) {
}
