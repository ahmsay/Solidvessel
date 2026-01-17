package com.solidvessel.account.adapter.in.address.rest.response;

public record AddressResponse(Long id, String name, String country, String city, String zipCode, Boolean isPrimary) {
}
