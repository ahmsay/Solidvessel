package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;

public interface ICustomerService {

    Iterable<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer);
}
