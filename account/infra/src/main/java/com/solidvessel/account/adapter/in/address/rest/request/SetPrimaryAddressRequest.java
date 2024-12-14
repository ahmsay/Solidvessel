package com.solidvessel.account.adapter.in.address.rest.request;

import com.solidvessel.account.address.service.command.SetPrimaryAddressCommand;
import com.solidvessel.shared.security.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record SetPrimaryAddressRequest(@NotNull Long id) {

    public SetPrimaryAddressCommand toCommand() {
        return new SetPrimaryAddressCommand(id, SessionUtil.getCurrentUserId());
    }
}
