package com.solidvessel.account.customer.service.command;

import com.solidvessel.account.customer.model.Address;

public record UpdateAddressCommand(String name, String country, String city, String zipcode, String customerId) {

    public Address toDomainModel() {
        return new Address(name, country, city, zipcode);
    }
}
