package com.shopping.accountservice.controllers;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.entity.Payment;
import com.shopping.accountservice.services.ICustomerService;
import com.shopping.accountservice.services.IPaymentRemoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final ICustomerService customerService;
    private final IPaymentRemoteService paymentRemoteService;

    public CustomerRestController(final ICustomerService customerService, final IPaymentRemoteService paymentRemoteService) {
        this.customerService = customerService;
        this.paymentRemoteService = paymentRemoteService;
    }

    @GetMapping("/")
    public Set<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") final String id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/{customerId}/payments")
    public List<Payment> getPaymentsOfCustomer(@PathVariable("customerId") final String id) {
        return paymentRemoteService.getPaymentsOfCustomer(id);
    }
}
