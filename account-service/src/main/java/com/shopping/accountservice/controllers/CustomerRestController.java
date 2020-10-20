package com.shopping.accountservice.controllers;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.services.ICustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")//TODO use aggregate root
public class CustomerRestController {

    private final ICustomerService customerService;

    public CustomerRestController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") final Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody final Customer customer) {
        return customerService.addCustomer(customer);
    }
}
