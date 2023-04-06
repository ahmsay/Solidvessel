package com.solidvessel.payment.domain.payment.service.command;

public record AddToCartCommand(Long customerId, Long productId) {
}
