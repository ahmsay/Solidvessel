package com.microshop.accountservice.services;

import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.wrapper.CustomerDTO;

public interface ICustomerService {

    Iterable<Customer> findAll();

    CustomerDTO findById(Long id, boolean pruned);

    Customer save(Customer customer);
}
