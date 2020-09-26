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
        customers.add(new Customer(1L, "Zorkov"));
        customers.add(new Customer(2L, "Lorne"));
        customers.add(new Customer(3L, "Matthias"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomerById(final long id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findAny()
                .orElse(null);
    }
}
