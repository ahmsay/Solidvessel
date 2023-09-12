package com.solidvessel.auth.signup.service.command;

import java.time.LocalDate;

public record SignUpCommand(String firstName, String lastName, String email, String password, LocalDate birthDate,
                            String phoneNumber) {
}
