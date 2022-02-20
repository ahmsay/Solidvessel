package com.microshop.account.controller;

import com.microshop.account.entity.Customer;
import com.microshop.account.response.CustomerDetailResponse;
import com.microshop.account.response.CustomerResponse;
import com.microshop.account.response.OrdersResponse;
import com.microshop.account.response.PaymentsResponse;
import com.microshop.account.service.CustomerService;
import com.microshop.account.service.OrderService;
import com.microshop.account.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final OrderService orderService;

    public CustomerController(CustomerService customerService, PaymentService paymentService, OrderService orderService) {
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @GetMapping()
    public List<CustomerResponse> getAll() {
        return customerService.getAll().stream().map(CustomerResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable final Long id) {
        return CustomerResponse.from(customerService.getById(id));
    }

    @GetMapping("/{id}/detail")
    public CustomerDetailResponse getDetailById(@PathVariable final Long id) {
        CustomerResponse customer = getById(id);
        PaymentsResponse payments = paymentService.getPaymentsOfCustomer(customer.id());
        OrdersResponse orders = orderService.getOrdersOfCustomer(customer.id());
        return new CustomerDetailResponse(customer.id(), customer.name(), payments, orders);
    }

    @PostMapping()
    public CustomerResponse add(@RequestBody final Customer customer) {
        return CustomerResponse.from(customerService.add(customer));
    }
}
