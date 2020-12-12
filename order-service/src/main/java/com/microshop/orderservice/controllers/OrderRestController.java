package com.microshop.orderservice.controllers;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.services.IOrderService;
import com.microshop.orderservice.wrapper.OrderDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderRestController {

    private final IOrderService orderService;

    public OrderRestController(final IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Iterable<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public OrderDTO findById(@PathVariable final Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/orders/ofCustomer/{customerId}")
    public Iterable<Order> findByCustomerId(@PathVariable final Long customerId) {
        return orderService.findByCustomerId(customerId);
    }

    @PostMapping("/orders")
    public Order save(@RequestBody final Order order) {
        return orderService.save(order);
    }
}
