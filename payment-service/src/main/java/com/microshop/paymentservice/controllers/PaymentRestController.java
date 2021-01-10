package com.microshop.paymentservice.controllers;

import com.microshop.paymentservice.dto.PaymentDTO;
import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.services.IPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    private final IPaymentService paymentService;

    public PaymentRestController(final IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping()
    public Iterable<Payment> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentDTO findById(@PathVariable final Long id) {
        return paymentService.findById(id);
    }

    @GetMapping("/{id}/pruned")
    public Payment findPrunedById(@PathVariable final Long id) {
        return paymentService.findPrunedById(id);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public Iterable<Payment> findByCustomerId(@PathVariable final Long customerId) {
        return paymentService.findByCustomerId(customerId);
    }

    @PostMapping()
    public Payment save(@RequestBody final Payment payment,
                        @RequestParam final List<Long> productIds) {
        return paymentService.save(payment, productIds);
    }
}
