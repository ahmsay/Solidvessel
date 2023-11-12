package com.solidvessel.account.adapter.in.address.rest.request;

import com.solidvessel.account.address.service.command.AddAddressCommand;
import com.solidvessel.shared.security.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record AddAddressRequest(
        @NotNull String name,
        @NotNull String country,
        @NotNull String city,
        @NotNull String zipcode
) {

    public AddAddressCommand toCommand() {
        return new AddAddressCommand(name, country, city, zipcode, SessionUtil.getCurrentUserId());
    }
}
