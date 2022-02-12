package com.microshop.accountservice.controller;

import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.response.CustomerDetailResponse;
import com.microshop.accountservice.response.CustomerResponse;
import com.microshop.accountservice.response.OrderResponse;
import com.microshop.accountservice.response.PaymentResponse;
import com.microshop.accountservice.service.CustomerService;
import com.microshop.accountservice.service.OrderService;
import com.microshop.accountservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final OrderService orderService;

    public CustomerRestController(CustomerService customerService, PaymentService paymentService, OrderService orderService) {
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @GetMapping()
    public List<CustomerResponse> findAll() {
        return customerService.findAll().stream().map(CustomerResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable final Long id) {
        return CustomerResponse.from(customerService.findById(id));
    }

    @GetMapping("/{id}/detail")
    public CustomerDetailResponse findDetailById(@PathVariable final Long id) {
        CustomerResponse customer = findById(id);
        List<PaymentResponse> payments = paymentService.findByCustomerId(customer.id());
        List<OrderResponse> orders = orderService.findByCustomerId(customer.id());
        return new CustomerDetailResponse(customer.id(), customer.name(), payments, orders);
    }

    @PostMapping()
    public CustomerResponse save(@RequestBody final Customer customer) {
        return CustomerResponse.from(customerService.save(customer));
    }
}
