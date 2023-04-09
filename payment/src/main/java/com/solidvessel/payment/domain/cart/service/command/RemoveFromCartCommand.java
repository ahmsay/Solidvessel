package com.solidvessel.payment.domain.cart.service.command;

public record RemoveFromCartCommand(Long customerId, Long productId) {
}
