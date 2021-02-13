package com.microshop.orderservice.controllers;

import com.microshop.orderservice.dto.OrderDTO;
import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public Iterable<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable final Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/{id}/pruned")
    public Order findPrunedById(@PathVariable final Long id) {
        return orderService.findPrunedById(id);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public Iterable<Order> findByCustomerId(@PathVariable final Long customerId) {
        return orderService.findByCustomerId(customerId);
    }

    @PostMapping()
    public Order save(@RequestBody final Order order) {
        return orderService.save(order);
    }
}
