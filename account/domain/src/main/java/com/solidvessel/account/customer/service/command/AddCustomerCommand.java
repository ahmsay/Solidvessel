package com.solidvessel.account.customer.service.command;

import com.solidvessel.account.customer.model.Customer;

import java.time.LocalDate;

public record AddCustomerCommand(String id, String firstName, String lastName, LocalDate birthDate, String email,
                                 String phoneNumber) {

    public Customer toDomainModel() {
        return Customer.newCustomer(id, firstName, lastName, birthDate, email, phoneNumber);
    }
}
