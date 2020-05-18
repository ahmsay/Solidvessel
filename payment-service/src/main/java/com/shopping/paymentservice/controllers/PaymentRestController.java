package com.shopping.paymentservice.controllers;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.services.IPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    private IPaymentService paymentService;

    public PaymentRestController(final IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public Set<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable("paymentId") final int id) {
        return paymentService.getPaymentById(id);
    }
}
