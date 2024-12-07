package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.service.command.ChangeProductAvailabilityCommand;
import jakarta.validation.constraints.NotNull;

public record ChangeProductAvailabilityRequest(@NotNull Long id, @NotNull Boolean availability) {

    public ChangeProductAvailabilityCommand toCommand() {
        return new ChangeProductAvailabilityCommand(id, availability);
    }
}
