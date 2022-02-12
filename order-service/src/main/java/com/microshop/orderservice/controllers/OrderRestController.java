package com.microshop.orderservice.controllers;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.response.CustomerResponse;
import com.microshop.orderservice.response.OrderDetailResponse;
import com.microshop.orderservice.response.OrderResponse;
import com.microshop.orderservice.response.PaymentResponse;
import com.microshop.orderservice.services.CustomerService;
import com.microshop.orderservice.services.OrderService;
import com.microshop.orderservice.services.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    public OrderRestController(OrderService orderService, CustomerService customerService, PaymentService paymentService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    @GetMapping()
    public List<OrderResponse> findAll() {
        return orderService.findAll().stream().map(OrderResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDetailResponse findById(@PathVariable final Long id) {
        OrderResponse order = findPrunedById(id);
        CustomerResponse customer = customerService.findById(order.customerId());
        PaymentResponse payment = paymentService.findById(order.paymentId());
        return new OrderDetailResponse(order.id(), order.status(), customer, payment);
    }

    @GetMapping("/{id}/pruned")
    public OrderResponse findPrunedById(@PathVariable final Long id) {
        return OrderResponse.from(orderService.findPrunedById(id));
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<OrderResponse> findByCustomerId(@PathVariable final Long customerId) {
        return orderService.findByCustomerId(customerId).stream().map(OrderResponse::from).collect(Collectors.toList());
    }

    @PostMapping()
    public Order save(@RequestBody final Order order) {
        return orderService.save(order);
    }
}
