package com.solidvessel.order.customer.datamodel;

import com.solidvessel.order.customer.model.Customer;

public record CustomerDataModel(String id, String firstName, String lastName) {

    public static CustomerDataModel from(Customer customer) {
        return new CustomerDataModel(customer.getId(), customer.getFirstName(), customer.getLastName());
    }
}
