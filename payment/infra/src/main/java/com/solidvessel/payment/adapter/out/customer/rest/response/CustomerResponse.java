package com.solidvessel.payment.adapter.out.customer.rest.response;

import com.solidvessel.payment.customer.model.Customer;

public record CustomerResponse(String id, String firstName, String lastName) {

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName());
    }
}
