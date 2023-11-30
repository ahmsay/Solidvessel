package com.solidvessel.payment.cart.service.command;

public record RemoveFromCartCommand(String customerId, Long productId) {
}
