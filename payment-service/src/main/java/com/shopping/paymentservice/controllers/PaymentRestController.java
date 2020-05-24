package com.shopping.paymentservice.controllers;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.services.IPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Payment getPaymentById(@PathVariable("paymentId") final String id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/withIds")
    public List<Payment> getPaymentsByIds(@RequestParam("paymentIds") final List<String> paymentIds) {
        return paymentService.getPaymentsByIds(paymentIds);
    }
}
