package com.shopping.accountservice.controllers;

import com.shopping.accountservice.entity.Customer;
import com.shopping.accountservice.entity.Order;
import com.shopping.accountservice.entity.Payment;
import com.shopping.accountservice.services.ICustomerService;
import com.shopping.accountservice.services.IOrderService;
import com.shopping.accountservice.services.IPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final ICustomerService customerService;
    private final IPaymentService paymentService;
    private final IOrderService orderService;

    public CustomerRestController(final ICustomerService customerService, final IPaymentService paymentService, final IOrderService orderService) {
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") final String id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/{customerId}/payments")
    public List<Payment> getPaymentsOfCustomer(@PathVariable("customerId") final String id) {
        return paymentService.getPaymentsOfCustomer(id);
    }

    @GetMapping("/{customerId}/orders")
    public List<Order> getOrdersOfCustomer(@PathVariable("customerId") final String id) {
        return orderService.getOrdersOfCustomer(id);
    }
}
