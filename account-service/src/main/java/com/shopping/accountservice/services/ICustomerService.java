package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;

public interface ICustomerService {

    Iterable<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer addCustomer(Customer customer);
}
