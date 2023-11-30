package com.solidvessel.account.adapter.in.address.rest.request;

import com.solidvessel.account.address.service.command.RemoveAddressCommand;
import com.solidvessel.shared.security.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record RemoveAddressRequest(@NotNull Long id) {

    public RemoveAddressCommand toCommand() {
        return new RemoveAddressCommand(id, SessionUtil.getCurrentUserId());
    }
}
