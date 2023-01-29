package com.solidvessel.inventory.controller;

import com.solidvessel.inventory.entity.Product;
import com.solidvessel.inventory.response.ProductResponse;
import com.solidvessel.inventory.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductResponse> getAll() {
        return productService.getAll().stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable final Long id) {
        return ProductResponse.from(productService.getById(id));
    }

    @GetMapping("/ofPayment/{paymentId}")
    List<ProductResponse> getByPaymentId(@PathVariable final Long paymentId) {
        return productService.getByPaymentId(paymentId).stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    @PostMapping()
    public ProductResponse add(@RequestBody final Product product) {
        return ProductResponse.from(productService.add(product));
    }
}
