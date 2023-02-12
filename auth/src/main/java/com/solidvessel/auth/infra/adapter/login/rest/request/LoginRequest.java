package com.solidvessel.auth.infra.adapter.login.rest.request;

import com.solidvessel.auth.domain.login.service.command.LoginCommand;

public record LoginRequest(String username, String password) {

    public LoginCommand toCommand() {
        return new LoginCommand(username, password);
    }
}
