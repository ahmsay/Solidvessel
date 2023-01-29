package com.solidvessel.order.controller;

import com.solidvessel.order.entity.CustomerOrder;
import com.solidvessel.order.response.CustomerResponse;
import com.solidvessel.order.response.OrderDetailResponse;
import com.solidvessel.order.response.OrderResponse;
import com.solidvessel.order.response.PaymentResponse;
import com.solidvessel.order.service.CustomerService;
import com.solidvessel.order.service.OrderService;
import com.solidvessel.order.service.PaymentService;
import com.solidvessel.shared.auth.SessionUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    public OrderController(OrderService orderService, CustomerService customerService, PaymentService paymentService) {
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
    public OrderDetailResponse getDetailById(@PathVariable final Long id, final HttpServletRequest request) {
        OrderResponse order = getById(id);
        String session = SessionUtil.getSession(request);
        CustomerResponse customer = customerService.getCustomerOfOrder(order.customerId(), session);
        PaymentResponse payment = paymentService.getPaymentOfOrder(order.paymentId(), session);
        return new OrderDetailResponse(order.id(), order.status(), customer, payment);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<OrderResponse> getByCustomerId(@PathVariable final Long customerId) {
        return orderService.getByCustomerId(customerId).stream().map(OrderResponse::from).collect(Collectors.toList());
    }

    @PostMapping()
    public OrderResponse add(@RequestBody final CustomerOrder order) {
        return OrderResponse.from(orderService.add(order));
    }
}
