package com.solidvessel.account.customer.port;

import com.solidvessel.account.customer.model.Customer;

import java.util.List;

public interface CustomerQueryPort {

    List<Customer> getAll();

    Customer getById(String id);
}
