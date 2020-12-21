package com.microshop.accountservice.controllers;

import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.services.ICustomerService;
import com.microshop.accountservice.wrapper.CustomerDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerRestController {

    private final ICustomerService customerService;

    public CustomerRestController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public Iterable<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO findById(@PathVariable final Long id,
                                @RequestParam(required = false) final boolean pruned) {
        return customerService.findById(id, pruned);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody final Customer customer) {
        return customerService.save(customer);
    }
}
