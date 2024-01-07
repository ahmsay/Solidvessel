package com.solidvessel.account.adapter.in.customer.rest.datamodel;

import com.solidvessel.account.customer.model.Customer;

import java.time.LocalDate;

public record CustomerDataModel(String id, String firstName, String lastName, LocalDate birthDate, String email,
                                String phoneNumber) {

    public static CustomerDataModel from(Customer customer) {
        return new CustomerDataModel(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }
}
