package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(String id);
}
