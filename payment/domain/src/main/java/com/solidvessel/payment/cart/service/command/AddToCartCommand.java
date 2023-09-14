package com.solidvessel.payment.cart.service.command;

public record AddToCartCommand(Long customerId, Long productId, int quantity) {
}
