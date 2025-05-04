package com.solidvessel.order.order.service.command;

public record DeliverOrderCommand(Long id, String recipient) {
}
