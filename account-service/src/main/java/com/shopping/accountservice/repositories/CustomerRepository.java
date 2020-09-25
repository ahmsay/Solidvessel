package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository {

    private List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>();
        customers.add(new Customer("1", "Zorkov"));
        customers.add(new Customer("2", "Lorne"));
        customers.add(new Customer("3", "Matthias"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomerById(final String id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
