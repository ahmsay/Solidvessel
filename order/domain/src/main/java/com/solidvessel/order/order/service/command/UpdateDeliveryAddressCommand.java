package com.solidvessel.order.order.service.command;

public record UpdateDeliveryAddressCommand(Long id, String customerId, String address) {
}
