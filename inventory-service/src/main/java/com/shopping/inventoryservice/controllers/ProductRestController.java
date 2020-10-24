package com.shopping.inventoryservice.controllers;

import com.shopping.inventoryservice.dto.ProductDTO;
import com.shopping.inventoryservice.entity.Payment;
import com.shopping.inventoryservice.entity.Product;
import com.shopping.inventoryservice.services.IPaymentService;
import com.shopping.inventoryservice.services.IProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController {

    private final IProductService productService;
    private final IPaymentService paymentService;

    public ProductRestController(final IProductService productService, final IPaymentService paymentService) {
        this.productService = productService;
        this.paymentService = paymentService;
    }

    @GetMapping("/products")
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable final Long id) {
        return productService.findById(id);
    }

    @GetMapping("/products/ofPayment/{paymentId}")
    public Iterable<Product> findByPaymentId(@PathVariable final Long paymentId) {
        return productService.findByPaymentId(paymentId);
    }

    @GetMapping("/products/{id}/payment")
    public Payment findPaymentOfProduct(@PathVariable final Long id) {
        return paymentService.findPaymentOfProduct(id);
    }

    @PostMapping("/products")
    public Product save(@RequestBody final Product product) {
        return productService.save(product);
    }

    @PutMapping("/products/setPaymentIds")
    public ProductDTO setPaymentIds(@RequestBody final ProductDTO productDTO) {
        return productService.setPaymentIds(productDTO);
    }
}
