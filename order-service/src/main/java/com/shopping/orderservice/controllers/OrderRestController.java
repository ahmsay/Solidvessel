package com.shopping.orderservice.controllers;

import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.services.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private IOrderService orderService;

    public OrderRestController(final IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public Set<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable("orderId") final String id) {
        return orderService.getOrderById(id);
    }
}
