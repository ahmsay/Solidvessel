package com.solidvessel.payment.cart.service.command;

public record RemoveFromCartCommand(Long customerId, Long productId) {
}
