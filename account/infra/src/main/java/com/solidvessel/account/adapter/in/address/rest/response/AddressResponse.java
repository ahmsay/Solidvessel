package com.solidvessel.account.adapter.in.address.rest.response;

import com.solidvessel.account.address.model.Address;

public record AddressResponse(String name, String country, String city, String zipcode) {

    public static AddressResponse from(Address address) {
        return new AddressResponse(address.getName(), address.getCountry(), address.getCity(), address.getZipCode());
    }
}
