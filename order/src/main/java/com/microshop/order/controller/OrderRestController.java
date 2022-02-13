package com.microshop.order.controller;

import com.microshop.order.entity.Order;
import com.microshop.order.response.CustomerResponse;
import com.microshop.order.response.OrderDetailResponse;
import com.microshop.order.response.OrderResponse;
import com.microshop.order.response.PaymentResponse;
import com.microshop.order.service.CustomerService;
import com.microshop.order.service.OrderService;
import com.microshop.order.service.PaymentService;
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
