package com.solidvessel.account.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Address {

    private String name;
    private String country;
    private String city;
    private String zipCode;
}
