package com.solidvessel.account.domain.appuser.event;

import com.solidvessel.account.domain.customer.service.command.AddCustomerCommand;

import java.time.LocalDate;

public record UserSavedEvent(Long userId, String firstName, String lastName, String email, LocalDate birthDate,
                             String phoneNumber) {

    public AddCustomerCommand toCommand() {
        return new AddCustomerCommand(userId, firstName, lastName, birthDate, email, phoneNumber);
    }
}
