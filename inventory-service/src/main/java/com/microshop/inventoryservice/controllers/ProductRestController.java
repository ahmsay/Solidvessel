package com.microshop.inventoryservice.controllers;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.services.IProductService;
import com.microshop.inventoryservice.wrapper.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    private final IProductService productService;

    public ProductRestController(final IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public ProductDTO findById(@PathVariable final Long id) {
        return productService.findById(id);
    }

    @GetMapping("/products/ofPayment/{paymentId}")
    public Iterable<Product> findByPaymentId(@PathVariable final Long paymentId) {
        return productService.findByPaymentId(paymentId);
    }

    @PostMapping("/products")
    public Product save(@RequestBody final Product product) {
        return productService.save(product);
    }

    @PutMapping("/products/setPaymentIds")
    public void setPaymentIds(@RequestParam final Long paymentId,
                                @RequestParam final List<Long> productIds) {
        productService.setPaymentIds(paymentId, productIds);
    }
}
