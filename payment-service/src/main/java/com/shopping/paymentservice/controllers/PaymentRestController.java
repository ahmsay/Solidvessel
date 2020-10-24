package com.shopping.paymentservice.controllers;

import com.shopping.paymentservice.dto.PaymentDTO;
import com.shopping.paymentservice.entity.Customer;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.services.ICustomerService;
import com.shopping.paymentservice.services.IPaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentRestController {

    private final IPaymentService paymentService;
    private final ICustomerService customerService;

    public PaymentRestController(final IPaymentService paymentService, final ICustomerService customerService) {
        this.paymentService = paymentService;
        this.customerService = customerService;
    }

    @GetMapping("/payments")
    public Iterable<Payment> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment findById(@PathVariable final Long id) {
        return paymentService.findById(id);
    }

    @GetMapping("/payments/ofCustomer/{customerId}")
    public Iterable<Payment> findByCustomerId(@PathVariable final Long customerId) {
        return paymentService.findByCustomerId(customerId);
    }

    @GetMapping("/payments/{id}/customer")
    public Customer findCustomerOfPayment(@PathVariable final Long id) {
        return customerService.findCustomerOfPayment(id);
    }

    @PostMapping("/payments")
    public Payment save(@RequestBody final PaymentDTO paymentDTO) {
        return paymentService.save(paymentDTO);
    }
}
