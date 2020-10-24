package com.shopping.accountservice.controllers;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.services.ICustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerRestController {

    private final ICustomerService customerService;

    public CustomerRestController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public Iterable<Customer> all() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer findById(@PathVariable final Long id) {
        return customerService.findById(id);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody final Customer customer) {
        return customerService.save(customer);
    }
}
