package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository {

    private List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>();
        customers.add(new Customer("1", "Zorkov", Collections.singletonList("1"), Collections.singletonList("1")));
        customers.add(new Customer("2", "Lorne", new ArrayList<>(Arrays.asList("2", "3")), new ArrayList<>(Arrays.asList("2", "3"))));
        customers.add(new Customer("3", "Matthias", new ArrayList<>(), new ArrayList<>()));
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
