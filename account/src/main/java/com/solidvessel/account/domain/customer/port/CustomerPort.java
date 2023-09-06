package com.solidvessel.account.domain.customer.port;

import com.solidvessel.account.domain.customer.model.Customer;

public interface CustomerPort {

    void save(Customer customer);
}
