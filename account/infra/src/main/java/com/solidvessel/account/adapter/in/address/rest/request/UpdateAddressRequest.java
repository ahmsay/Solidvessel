package com.solidvessel.account.adapter.in.address.rest.request;

import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import com.solidvessel.shared.security.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record UpdateAddressRequest(
        @NotNull Long id,
        @NotNull String name,
        @NotNull String country,
        @NotNull String city,
        @NotNull String zipcode
) {
    public UpdateAddressCommand toCommand() {
        return new UpdateAddressCommand(id, name, country, city, zipcode, SessionUtil.getCurrentUserId());
    }
}
