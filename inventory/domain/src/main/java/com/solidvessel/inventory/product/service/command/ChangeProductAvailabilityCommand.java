package com.solidvessel.inventory.product.service.command;

public record ChangeProductAvailabilityCommand(Long id, Boolean isAvailable) {
}
