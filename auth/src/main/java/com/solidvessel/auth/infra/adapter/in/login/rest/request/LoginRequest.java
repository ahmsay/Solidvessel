package com.solidvessel.auth.infra.adapter.in.login.rest.request;

import com.solidvessel.auth.domain.login.service.command.LoginCommand;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotNull String email, @NotNull String password) {

    public LoginCommand toCommand() {
        return new LoginCommand(email, password);
    }
}
