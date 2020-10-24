package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.repositories.ICustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
    public Customer getCustomerById(final Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer addCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }
}
