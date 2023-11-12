package com.solidvessel.payment.appuser.event;

import com.solidvessel.payment.cart.service.command.CreateCartCommand;

import java.time.LocalDate;

public record UserSavedEvent(String userId, String firstName, String lastName, String email, LocalDate birthDate,
                             String phoneNumber) {

    public CreateCartCommand toCommand() {
        return new CreateCartCommand(userId);
    }
}
