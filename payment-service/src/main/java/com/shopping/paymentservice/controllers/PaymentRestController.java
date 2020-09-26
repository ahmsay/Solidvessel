package com.shopping.paymentservice.controllers;

import com.shopping.paymentservice.entity.Customer;
import com.shopping.paymentservice.entity.Order;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.services.ICustomerService;
import com.shopping.paymentservice.services.IOrderService;
import com.shopping.paymentservice.services.IPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    private IPaymentService paymentService;
    private ICustomerService customerService;
    private IOrderService orderService;

    public PaymentRestController(final IPaymentService paymentService, final ICustomerService customerService, final IOrderService orderService) {
        this.paymentService = paymentService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable("paymentId") final long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<Payment> getPaymentsOfCustomer(@PathVariable("customerId") final long customerId) {
        return paymentService.getPaymentsOfCustomer(customerId);
    }

    @GetMapping("/{paymentId}/customer")
    public Customer getCustomerOfPayment(@PathVariable("paymentId") final long id) {
        return customerService.getCustomerOfPayment(id);
    }

    @GetMapping("/{paymentId}/order")
    public Order getOrderOfPayment(@PathVariable("paymentId") final long id) {
        return orderService.getOrderOfPayment(id);
    }
}
