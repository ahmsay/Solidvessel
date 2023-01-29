package com.solidvessel.account.controller;

import com.solidvessel.account.response.CustomerDetailResponse;
import com.solidvessel.account.response.CustomerResponse;
import com.solidvessel.account.response.OrdersResponse;
import com.solidvessel.account.response.PaymentsResponse;
import com.solidvessel.account.service.CustomerService;
import com.solidvessel.account.service.OrderService;
import com.solidvessel.account.service.PaymentService;
import com.solidvessel.shared.auth.SessionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public CustomerDetailResponse getDetailById(@PathVariable final Long id, final HttpServletRequest request) {
        CustomerResponse customer = getById(id);
        String session = SessionUtil.getSession(request);
        PaymentsResponse payments = paymentService.getPaymentsOfCustomer(customer.id(), session);
        OrdersResponse orders = orderService.getOrdersOfCustomer(customer.id(), session);
        return new CustomerDetailResponse(customer.id(), customer.firstName(), customer.lastName(), payments, orders);
    }
}
