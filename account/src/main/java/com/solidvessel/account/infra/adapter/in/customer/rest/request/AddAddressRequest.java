package com.solidvessel.account.infra.adapter.in.customer.rest.request;

import com.solidvessel.account.domain.customer.service.command.AddAddressCommand;
import com.solidvessel.shared.infra.util.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record AddAddressRequest(
        @NotNull String name,
        @NotNull String country,
        String city,
        String zipcode
) {

    public AddAddressCommand toCommand() {
        return new AddAddressCommand(name, country, city, zipcode, SessionUtil.getCurrentUserId());
    }
}
