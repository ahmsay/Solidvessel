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
    public List<OrderResponse> getAll() {
        return orderService.getAll().stream().map(OrderResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable final Long id) {
        return OrderResponse.from(orderService.getById(id));
    }

    @GetMapping("/{id}/detail")
    public OrderDetailResponse getDetailById(@PathVariable final Long id) {
        OrderResponse order = getById(id);
        CustomerResponse customer = customerService.getById(order.customerId());
        PaymentResponse payment = paymentService.getById(order.paymentId());
        return new OrderDetailResponse(order.id(), order.status(), customer, payment);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<OrderResponse> getByCustomerId(@PathVariable final Long customerId) {
        return orderService.getByCustomerId(customerId).stream().map(OrderResponse::from).collect(Collectors.toList());
    }

    @PostMapping()
    public OrderResponse add(@RequestBody final Order order) {
        return OrderResponse.from(orderService.add(order));
    }
}
