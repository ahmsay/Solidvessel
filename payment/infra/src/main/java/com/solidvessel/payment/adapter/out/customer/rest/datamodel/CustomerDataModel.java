package com.solidvessel.payment.adapter.out.customer.rest.datamodel;

import com.solidvessel.payment.customer.model.Customer;

public record CustomerDataModel(String id, String firstName, String lastName) {

    public static CustomerDataModel from(Customer customer) {
        return new CustomerDataModel(customer.getId(), customer.getFirstName(), customer.getLastName());
    }
}
