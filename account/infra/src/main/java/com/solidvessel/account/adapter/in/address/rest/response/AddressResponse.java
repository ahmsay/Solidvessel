package com.solidvessel.account.adapter.in.address.rest.response;

import com.solidvessel.account.address.model.Address;

public record AddressResponse(Long id, String name, String country, String city, String zipcode, Boolean isPrimary) {

    public static AddressResponse from(Address address) {
        return new AddressResponse(address.getId(), address.getName(), address.getCountry(), address.getCity(), address.getZipCode(), address.getIsPrimary());
    }
}
