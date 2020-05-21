package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.repositories.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerService implements ICustomerService {

    private ICustomerRepository customerRepository;

    public CustomerService(final ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Set<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(final int id) {
        return customerRepository.getCustomerById(id);
    }
}
