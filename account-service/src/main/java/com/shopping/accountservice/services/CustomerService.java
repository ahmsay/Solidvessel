package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.repositories.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(final ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(final Long id) {
        return customerRepository.findById(id);
    }
}
