package com.solidvessel.payment.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {

    private String id;
    private String address;

    public void updateAddress(String address) {
        this.address = address;
    }
}
