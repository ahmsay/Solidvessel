package com.microshop.accountservice.services;

import com.microshop.accountservice.dto.CustomerDTO;
import com.microshop.accountservice.entity.Customer;

public interface ICustomerService {

    Iterable<Customer> findAll();

    CustomerDTO findById(Long id);

    Customer save(Customer customer);
}