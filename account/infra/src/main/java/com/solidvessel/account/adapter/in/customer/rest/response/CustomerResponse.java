package com.solidvessel.account.adapter.in.customer.rest.response;

import com.solidvessel.account.customer.model.Customer;

import java.time.LocalDate;

public record CustomerResponse(String id, String firstName, String lastName, LocalDate birthDate, String email,
                               String phoneNumber) {

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }
}
