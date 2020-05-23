package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;

import java.util.Set;

public interface ICustomerService {

    Set<Customer> getAllCustomers();

    Customer getCustomerById(String id);
}
