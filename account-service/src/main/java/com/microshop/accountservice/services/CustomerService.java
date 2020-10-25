package com.microshop.accountservice.services;

import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.repositories.ICustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(final ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(final Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer save(final Customer customer) {
        return customerRepository.save(customer);
    }
}
