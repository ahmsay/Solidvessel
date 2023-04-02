package com.solidvessel.account.domain.customer.service.command;

import com.solidvessel.account.domain.customer.model.Address;

public record AddAddressCommand(String name, String country, String city, String zipcode) {

    public Address toDomainModel() {
        return new Address(name, country, city, zipcode);
    }
}
