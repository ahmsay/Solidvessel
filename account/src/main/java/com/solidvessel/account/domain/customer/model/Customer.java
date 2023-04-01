package com.solidvessel.account.domain.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private List<Address> addresses;

    public static Customer newCustomer(Long id, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber) {
        return new Customer(id, firstName, lastName, birthDate, email, phoneNumber, null);
    }
}
