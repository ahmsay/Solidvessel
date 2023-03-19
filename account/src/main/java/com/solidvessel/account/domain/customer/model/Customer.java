package com.solidvessel.account.domain.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
}
