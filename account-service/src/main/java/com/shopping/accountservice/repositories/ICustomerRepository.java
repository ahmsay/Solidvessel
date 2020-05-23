package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Customer;

import java.util.Set;

public interface ICustomerRepository {

    Set<Customer> getAllCustomers();

    Customer getCustomerById(String id);
}
