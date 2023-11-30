package com.solidvessel.order.order.service.command;

public record AddOrderCommand(String customerId, Long paymentId) {
}
