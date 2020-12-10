package com.microshop.accountservice.controllers;

import com.microshop.accountservice.dto.CustomerDTO;
import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.services.ICustomerService;
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
    public CustomerDTO findById(@PathVariable final Long id) {
        return customerService.findById(id);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody final Customer customer) {
        return customerService.save(customer);
    }
}
