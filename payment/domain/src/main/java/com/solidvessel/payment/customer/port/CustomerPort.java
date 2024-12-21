package com.solidvessel.payment.customer.port;

import com.solidvessel.payment.customer.model.Customer;

public interface CustomerPort {

    void save(Customer customer);
}
