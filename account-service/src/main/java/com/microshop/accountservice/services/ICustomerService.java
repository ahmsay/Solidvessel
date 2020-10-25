package com.microshop.accountservice.services;

import com.microshop.accountservice.entity.Customer;

public interface ICustomerService {

    Iterable<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer);
}
