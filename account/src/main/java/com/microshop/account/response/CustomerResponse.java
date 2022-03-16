package com.microshop.account.response;

import com.microshop.account.entity.Customer;

public record CustomerResponse(Long id, String firstName, String lastName) {

    public static CustomerResponse from(final Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName());
    }
}
