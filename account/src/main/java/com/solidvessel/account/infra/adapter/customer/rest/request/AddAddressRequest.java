package com.solidvessel.account.infra.adapter.customer.rest.request;

import com.solidvessel.account.domain.customer.service.command.AddAddressCommand;
import jakarta.validation.constraints.NotNull;

public record AddAddressRequest(
        @NotNull String name,
        @NotNull String country,
        String city,
        String zipcode
) {

    public AddAddressCommand toCommand() {
        return new AddAddressCommand(name, country, city, zipcode);
    }
}