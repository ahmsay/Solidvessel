package com.microshop.payment.controller;

import com.microshop.payment.request.AddPaymentRequest;
import com.microshop.payment.response.CustomerResponse;
import com.microshop.payment.response.PaymentDetailResponse;
import com.microshop.payment.response.PaymentResponse;
import com.microshop.payment.response.ProductsResponse;
import com.microshop.payment.service.CustomerService;
import com.microshop.payment.service.PaymentService;
import com.microshop.payment.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final CustomerService customerService;
    private final ProductService productService;

    public PaymentController(PaymentService paymentService, CustomerService customerService, ProductService productService) {
        this.paymentService = paymentService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping()
    public List<PaymentResponse> getAll() {
        return paymentService.getAll().stream().map(PaymentResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable final Long id) {
        return PaymentResponse.from(paymentService.getById(id));
    }

    @GetMapping("/{id}/detail")
    public PaymentDetailResponse getDetailById(@PathVariable final Long id) {
        PaymentResponse payment = getById(id);
        CustomerResponse customer = customerService.getCustomerOfPayment(payment.customerId());
        ProductsResponse products = productService.getProductsOfPayment(payment.id());
        return new PaymentDetailResponse(payment.id(), payment.totalCharge(), customer, products);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<PaymentResponse> getByCustomerId(@PathVariable final Long customerId) {
        return paymentService.getByCustomerId(customerId).stream().map(PaymentResponse::from).collect(Collectors.toList());
    }

    @PostMapping()
    public PaymentResponse add(@RequestBody final AddPaymentRequest request) {
        return PaymentResponse.from(paymentService.add(request));
    }
}
