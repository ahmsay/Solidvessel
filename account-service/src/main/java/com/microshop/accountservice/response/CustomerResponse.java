package com.microshop.accountservice.response;

import com.microshop.accountservice.entity.Customer;

public record CustomerResponse(Long id, String name) {

    public static CustomerResponse from(final Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName());
    }
}
