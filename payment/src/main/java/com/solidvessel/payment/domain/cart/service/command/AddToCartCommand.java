package com.solidvessel.payment.domain.cart.service.command;

public record AddToCartCommand(Long customerId, Long productId) {
}
