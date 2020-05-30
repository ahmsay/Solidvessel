package com.shopping.orderservice.controllers;

import com.shopping.orderservice.entity.Customer;
import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.entity.Payment;
import com.shopping.orderservice.services.ICustomerRemoteService;
import com.shopping.orderservice.services.IOrderService;
import com.shopping.orderservice.services.IPaymentRemoteService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private IOrderService orderService;
    private ICustomerRemoteService customerRemoteService;
    private IPaymentRemoteService paymentRemoteService;

    public OrderRestController(final IOrderService orderService, final ICustomerRemoteService customerRemoteService, final IPaymentRemoteService paymentRemoteService) {
        this.orderService = orderService;
        this.customerRemoteService = customerRemoteService;
        this.paymentRemoteService = paymentRemoteService;
    }

    @GetMapping("/")
    public Set<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable("orderId") final String id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/withIds")
    public Set<Order> getOrdersByIds(@RequestParam("orderIds") final Set<String> orderIds) {
        return orderService.getOrdersByIds(orderIds);
    }

    @GetMapping("/{orderId}/customer")
    public Customer getCustomerOfOrder(@PathVariable("orderId") final String id) {
        return customerRemoteService.getCustomerOfOrder(id);
    }

    @GetMapping("/{orderId}/payment")
    public Payment getPaymentOfOrder(@PathVariable("orderId") final String id) {
        return paymentRemoteService.getPaymentOfOrder(id);
    }
}
