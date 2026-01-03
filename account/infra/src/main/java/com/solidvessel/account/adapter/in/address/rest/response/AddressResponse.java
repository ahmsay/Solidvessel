package com.solidvessel.account.adapter.in.address.rest.response;

import com.solidvessel.account.adapter.in.address.mapper.AddressWebMapper;
import com.solidvessel.account.address.model.Address;

public record AddressResponse(Long id, String name, String country, String city, String zipCode, Boolean isPrimary) {

    public static AddressResponse from(Address address) {
        return AddressWebMapper.INSTANCE.toResponse(address);
    }
}
