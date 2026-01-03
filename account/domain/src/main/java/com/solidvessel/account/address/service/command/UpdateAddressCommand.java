package com.solidvessel.account.address.service.command;

public record UpdateAddressCommand(Long id, String name, String country, String city, String zipCode,
                                   String customerId) {
}
