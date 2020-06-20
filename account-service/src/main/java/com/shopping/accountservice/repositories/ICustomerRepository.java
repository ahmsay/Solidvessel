package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Customer;

import java.util.List;

public interface ICustomerRepository {

    List<Customer> getAllCustomers();

    Customer getCustomerById(String id);
}
