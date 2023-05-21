package com.solidvessel.account.domain.customer.service.command;

import com.solidvessel.account.domain.customer.model.Address;

public record UpdateAddressCommand(String name, String country, String city, String zipcode, Long customerId) {

    public Address toDomainModel() {
        return new Address(name, country, city, zipcode);
    }
}
