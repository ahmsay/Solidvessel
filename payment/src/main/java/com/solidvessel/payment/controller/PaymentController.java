package com.solidvessel.payment.controller;

import com.solidvessel.payment.request.AddPaymentRequest;
import com.solidvessel.payment.response.CustomerResponse;
import com.solidvessel.payment.response.PaymentDetailResponse;
import com.solidvessel.payment.response.PaymentResponse;
import com.solidvessel.payment.response.ProductsResponse;
import com.solidvessel.payment.service.CustomerService;
import com.solidvessel.payment.service.PaymentService;
import com.solidvessel.payment.service.ProductService;
import com.solidvessel.shared.auth.SessionUtil;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
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
    public PaymentDetailResponse getDetailById(@PathVariable final Long id, final HttpServletRequest request) {
        PaymentResponse payment = getById(id);
        String session = SessionUtil.getSession(request);
        CustomerResponse customer = customerService.getCustomerOfPayment(payment.customerId(), session);
        ProductsResponse products = productService.getProductsOfPayment(payment.id(), session);
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
