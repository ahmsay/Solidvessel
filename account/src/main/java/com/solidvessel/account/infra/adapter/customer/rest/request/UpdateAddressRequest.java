package com.solidvessel.account.infra.adapter.customer.rest.request;

import com.solidvessel.account.domain.customer.service.command.UpdateAddressCommand;
import jakarta.validation.constraints.NotNull;

public record UpdateAddressRequest(
        @NotNull String name,
        @NotNull String country,
        String city,
        String zipcode
) {
    public UpdateAddressCommand toCommand() {
        return new UpdateAddressCommand(name, country, city, zipcode);
    }
}
