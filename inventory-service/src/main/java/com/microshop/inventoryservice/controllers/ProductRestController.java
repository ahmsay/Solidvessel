package com.microshop.inventoryservice.controllers;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.services.IProductService;
import com.microshop.inventoryservice.wrapper.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final IProductService productService;

    public ProductRestController(final IProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable final Long id) {
        return productService.findById(id);
    }

    @GetMapping("/{id}/pruned")
    public Product findPrunedById(@PathVariable final Long id) {
        return productService.findPrunedById(id);
    }

    @GetMapping("/ofPayment/{paymentId}")
    public Iterable<Product> findByPaymentId(@PathVariable final Long paymentId) {
        return productService.findByPaymentId(paymentId);
    }

    @PostMapping()
    public Product save(@RequestBody final Product product) {
        return productService.save(product);
    }

    @PutMapping("/setPaymentIds")
    public void setPaymentIds(@RequestParam final Long paymentId,
                              @RequestParam final List<Long> productIds) {
        productService.setPaymentIds(paymentId, productIds);
    }
}
