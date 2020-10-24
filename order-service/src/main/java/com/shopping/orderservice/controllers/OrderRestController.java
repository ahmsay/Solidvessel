package com.shopping.orderservice.controllers;

import com.shopping.orderservice.entity.Customer;
import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.entity.Payment;
import com.shopping.orderservice.services.ICustomerService;
import com.shopping.orderservice.services.IOrderService;
import com.shopping.orderservice.services.IPaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderRestController {

    private final IOrderService orderService;
    private final ICustomerService customerService;
    private final IPaymentService paymentService;

    public OrderRestController(final IOrderService orderService, final ICustomerService customerService, final IPaymentService paymentService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    @GetMapping("/orders")
    public Iterable<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order findById(@PathVariable final Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/orders/ofCustomer/{customerId}")
    public Iterable<Order> findByCustomerId(@PathVariable final Long customerId) {
        return orderService.findByCustomerId(customerId);
    }

    @GetMapping("/orders/{id}/customer")
    public Customer findCustomerOfOrder(@PathVariable final Long id) {
        return customerService.findCustomerOfOrder(id);
    }

    @GetMapping("/orders/{id}/payment")
    public Payment findPaymentOfOrder(@PathVariable final Long id) {
        return paymentService.findPaymentOfOrder(id);
    }

    @PostMapping("/orders")
    public Order save(@RequestBody final Order order) {
        return orderService.save(order);
    }
}
