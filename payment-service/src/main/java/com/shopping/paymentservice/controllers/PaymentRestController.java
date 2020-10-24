package com.shopping.paymentservice.controllers;

import com.shopping.paymentservice.dto.PaymentDTO;
import com.shopping.paymentservice.entity.Customer;
import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.services.ICustomerService;
import com.shopping.paymentservice.services.IPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    private final IPaymentService paymentService;
    private final ICustomerService customerService;

    public PaymentRestController(final IPaymentService paymentService, final ICustomerService customerService) {
        this.paymentService = paymentService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable("paymentId") final Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<Payment> getPaymentsOfCustomer(@PathVariable("customerId") final Long customerId) {
        return paymentService.getPaymentsOfCustomer(customerId);
    }

    @GetMapping("/{paymentId}/customer")
    public Customer getCustomerOfPayment(@PathVariable("paymentId") final Long id) {
        return customerService.getCustomerOfPayment(id);
    }

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody final PaymentDTO paymentDTO) {
        return paymentService.addPayment(paymentDTO);
    }
}
