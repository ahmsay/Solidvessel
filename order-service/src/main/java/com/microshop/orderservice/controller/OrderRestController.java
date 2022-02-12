package com.microshop.orderservice.controller;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.response.CustomerResponse;
import com.microshop.orderservice.response.OrderDetailResponse;
import com.microshop.orderservice.response.OrderResponse;
import com.microshop.orderservice.response.PaymentResponse;
import com.microshop.orderservice.service.CustomerService;
import com.microshop.orderservice.service.OrderService;
import com.microshop.orderservice.service.PaymentService;
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
    public OrderResponse findById(@PathVariable final Long id) {
        return OrderResponse.from(orderService.findById(id));
    }

    @GetMapping("/{id}/detail")
    public OrderDetailResponse findDetailById(@PathVariable final Long id) {
        OrderResponse order = findById(id);
        CustomerResponse customer = customerService.findById(order.customerId());
        PaymentResponse payment = paymentService.findById(order.paymentId());
        return new OrderDetailResponse(order.id(), order.status(), customer, payment);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<OrderResponse> findByCustomerId(@PathVariable final Long customerId) {
        return orderService.findByCustomerId(customerId).stream().map(OrderResponse::from).collect(Collectors.toList());
    }

    @PostMapping()
    public OrderResponse save(@RequestBody final Order order) {
        return OrderResponse.from(orderService.save(order));
    }
}
