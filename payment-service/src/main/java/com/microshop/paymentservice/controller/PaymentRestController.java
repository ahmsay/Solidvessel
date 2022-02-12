package com.microshop.paymentservice.controller;

import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.request.SavePaymentRequest;
import com.microshop.paymentservice.response.CustomerResponse;
import com.microshop.paymentservice.response.PaymentDetailResponse;
import com.microshop.paymentservice.response.PaymentResponse;
import com.microshop.paymentservice.response.ProductResponse;
import com.microshop.paymentservice.service.CustomerService;
import com.microshop.paymentservice.service.PaymentService;
import com.microshop.paymentservice.service.ProductService;
import com.microshop.paymentservice.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

    private final PaymentService paymentService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final SaleService saleService;

    public PaymentRestController(PaymentService paymentService, CustomerService customerService, ProductService productService, SaleService saleService) {
        this.paymentService = paymentService;
        this.customerService = customerService;
        this.productService = productService;
        this.saleService = saleService;
    }

    @GetMapping()
    public List<PaymentResponse> findAll() {
        return paymentService.findAll().stream().map(PaymentResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PaymentDetailResponse findById(@PathVariable final Long id) {
        PaymentResponse payment = findPrunedById(id);
        CustomerResponse customer = customerService.findById(payment.customerId());
        List<ProductResponse> products = findProductsOfPayment(payment.id());
        return new PaymentDetailResponse(payment.id(), payment.totalCharge(), customer, products);
    }

    @GetMapping("/{id}/pruned")
    public PaymentResponse findPrunedById(@PathVariable final Long id) {
        return PaymentResponse.from(paymentService.findPrunedById(id));
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<PaymentResponse> findByCustomerId(@PathVariable final Long customerId) {
        return paymentService.findByCustomerId(customerId).stream().map(PaymentResponse::from).collect(Collectors.toList());
    }

    @PostMapping()
    public Payment save(@RequestBody final SavePaymentRequest request) {
        return paymentService.save(request);
    }

    private List<ProductResponse> findProductsOfPayment(final Long paymentId) {
        List<Sale> sales = (List<Sale>) saleService.findByPaymentId(paymentId);
        List<Long> productIds = sales.stream().map(Sale::getProductId).collect(Collectors.toList());
        return productService.findByIds(productIds);
    }
}
