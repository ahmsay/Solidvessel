package com.solidvessel.account.address.service.command;

import com.solidvessel.account.address.model.Address;

public record AddAddressCommand(String name, String country, String city, String zipcode, String customerId) {

    public Address toDomainModel() {
        return new Address(customerId, name, country, city, zipcode, false);
    }
}
