package com.microshop.account.service;

import com.microshop.account.entity.Customer;
import com.microshop.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(final Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
    }

    public Customer add(final Customer customer) {
        return customerRepository.save(customer);
    }
}
