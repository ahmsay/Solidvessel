package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long id);
}
