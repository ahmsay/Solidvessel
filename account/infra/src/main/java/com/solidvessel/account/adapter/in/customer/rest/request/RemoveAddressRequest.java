package com.solidvessel.account.adapter.in.customer.rest.request;

import com.solidvessel.account.customer.service.command.RemoveAddressCommand;
import com.solidvessel.shared.security.SessionUtil;
import jakarta.validation.constraints.NotNull;

public record RemoveAddressRequest(@NotNull String name) {

    public RemoveAddressCommand toCommand() {
        return new RemoveAddressCommand(name, SessionUtil.getCurrentUserId());
    }
}
