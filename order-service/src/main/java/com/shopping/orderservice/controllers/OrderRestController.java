package com.shopping.orderservice.controllers;

import com.shopping.orderservice.entity.Customer;
import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.entity.Payment;
import com.shopping.orderservice.services.ICustomerService;
import com.shopping.orderservice.services.IOrderService;
import com.shopping.orderservice.services.IPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private final IOrderService orderService;
    private final ICustomerService customerService;
    private final IPaymentService paymentService;

    public OrderRestController(final IOrderService orderService, final ICustomerService customerService, final IPaymentService paymentService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable("orderId") final Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<Order> getOrdersOfCustomer(@PathVariable("customerId") final Long customerId) {
        return orderService.getOrdersOfCustomer(customerId);
    }

    @GetMapping("/{orderId}/customer")
    public Customer getCustomerOfOrder(@PathVariable("orderId") final Long id) {
        return customerService.getCustomerOfOrder(id);
    }

    @GetMapping("/{orderId}/payment")
    public Payment getPaymentOfOrder(@PathVariable("orderId") final Long id) {
        return paymentService.getPaymentOfOrder(id);
    }

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody final Order order) {
        return orderService.addOrder(order);
    }
}
