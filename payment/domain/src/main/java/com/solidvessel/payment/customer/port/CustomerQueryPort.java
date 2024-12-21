package com.solidvessel.payment.customer.port;

import com.solidvessel.payment.customer.model.Customer;

import java.util.Optional;

public interface CustomerQueryPort {

    Optional<Customer> getById(String id);
}
