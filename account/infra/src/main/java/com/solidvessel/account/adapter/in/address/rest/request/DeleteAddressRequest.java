package com.solidvessel.account.adapter.in.address.rest.request;

import com.solidvessel.account.address.service.command.DeleteAddressCommand;
import com.solidvessel.shared.security.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record DeleteAddressRequest(@NotNull Long id) {

    public DeleteAddressCommand toCommand() {
        return new DeleteAddressCommand(id, SessionUtil.getCurrentUserId());
    }
}
