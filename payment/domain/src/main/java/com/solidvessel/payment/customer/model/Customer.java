package com.solidvessel.payment.customer.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Customer {

    private String id;
    private String address;

    public void updateAddress(String address) {
        this.address = address;
    }
}
