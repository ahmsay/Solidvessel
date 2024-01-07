package com.solidvessel.account.adapter.in.address.rest.datamodel;

import com.solidvessel.account.address.model.Address;

public record AddressDataModel(String name, String country, String city, String zipcode) {

    public static AddressDataModel from(Address address) {
        return new AddressDataModel(address.getName(), address.getCountry(), address.getCity(), address.getZipCode());
    }
}
