package com.microshop.accountservice.controllers;

import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.services.ICustomerService;
import com.microshop.accountservice.wrapper.CustomerDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final ICustomerService customerService;

    public CustomerRestController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public Iterable<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable final Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/{id}/pruned")
    public Customer findByIdPruned(@PathVariable final Long id) {
        return customerService.findPrunedById(id);
    }

    @PostMapping()
    public Customer addCustomer(@RequestBody final Customer customer) {
        return customerService.save(customer);
    }
}
