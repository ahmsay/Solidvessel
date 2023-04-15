package com.solidvessel.order.domain.order.service.command;

public record AddOrderCommand(Long customerId, Long paymentId) {
}
