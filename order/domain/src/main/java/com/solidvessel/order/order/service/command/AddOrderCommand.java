package com.solidvessel.order.order.service.command;

public record AddOrderCommand(Long customerId, Long paymentId) {
}
