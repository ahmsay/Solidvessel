package com.solidvessel.auth.infra.adapter.signup.rest.request;

import com.solidvessel.auth.domain.signup.service.command.SignUpCommand;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SignUpRequest(
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull String email,
        @NotNull String password,
        LocalDate birthDate,
        String phoneNumber
) {

    public SignUpCommand toCommand() {
        return new SignUpCommand(firstName, lastName, email, password, birthDate, phoneNumber);
    }
}
