package com.solidvessel.auth.infra.adapter.signup.rest.request;

import com.solidvessel.auth.domain.signup.service.command.SignUpCommand;

public record SignUpRequest(String firstName, String lastName, String username, String password) {

    public SignUpCommand toCommand() {
        return new SignUpCommand(firstName, lastName, username, password);
    }
}
