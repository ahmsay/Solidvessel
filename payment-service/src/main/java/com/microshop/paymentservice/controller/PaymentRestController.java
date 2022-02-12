package com.microshop.paymentservice.controller;

import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.request.AddPaymentRequest;
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
        CustomerResponse customer = customerService.getById(payment.customerId());
        List<ProductResponse> products = getProductsOfPayment(payment.id());
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

    private List<ProductResponse> getProductsOfPayment(final Long paymentId) {
        List<Sale> sales = (List<Sale>) saleService.getByPaymentId(paymentId);
        List<Long> productIds = sales.stream().map(Sale::getProductId).collect(Collectors.toList());
        return productService.getByIds(productIds);
    }
}
