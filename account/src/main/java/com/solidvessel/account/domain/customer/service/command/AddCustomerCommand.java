package com.solidvessel.account.domain.customer.service.command;

import com.solidvessel.account.domain.customer.model.Customer;

import java.time.LocalDate;

public record AddCustomerCommand(Long id, String firstName, String lastName, LocalDate birthDate, String email,
                                 String phoneNumber) {

    public Customer toDomainModel() {
        return Customer.newCustomer(id, firstName, lastName, birthDate, email, phoneNumber);
    }
}
