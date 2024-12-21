package com.solidvessel.payment.address.event;

import com.solidvessel.payment.customer.model.Customer;

public record PrimaryAddressSavedEvent(String customerId, String address) {

    public Customer toDomainModel() {
        return new Customer(customerId, address);
    }
}
