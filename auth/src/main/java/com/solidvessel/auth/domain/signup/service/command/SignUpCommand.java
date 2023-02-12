package com.solidvessel.auth.domain.signup.service.command;

public record SignUpCommand(String firstName, String lastName, String username, String password) {
}
