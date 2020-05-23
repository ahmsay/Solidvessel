package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CustomerRepository implements ICustomerRepository {

    private Set<Customer> customers;

    public CustomerRepository() {
        customers = new HashSet<>();
        customers.add(new Customer("1", "Zorkov", new HashSet<>(Collections.singletonList("1")), new HashSet<>(Collections.singletonList("1"))));
        customers.add(new Customer("2", "Lorne", new HashSet<>(Arrays.asList("2", "3")), new HashSet<>(Arrays.asList("2", "3"))));
        customers.add(new Customer("3", "Matthias", new HashSet<>(), new HashSet<>()));
    }

    @Override
    public Set<Customer> getAllCustomers() {
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
