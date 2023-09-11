package com.solidvessel.account.adapter.in.customer.rest.request;

import com.solidvessel.account.customer.service.command.UpdateAddressCommand;
import com.solidvessel.shared.infra.util.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record UpdateAddressRequest(
        @NotNull String name,
        @NotNull String country,
        String city,
        String zipcode
) {
    public UpdateAddressCommand toCommand() {
        return new UpdateAddressCommand(name, country, city, zipcode, SessionUtil.getCurrentUserId());
    }
}
