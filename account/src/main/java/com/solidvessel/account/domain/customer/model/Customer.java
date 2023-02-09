package com.solidvessel.account.domain.customer.model;

public class Customer {

    private Long id;
    private String firstName;
    private String lastName;

    public Customer(final Long id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
