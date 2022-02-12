package com.microshop.inventoryservice.controllers;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.response.ProductResponse;
import com.microshop.inventoryservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductResponse> findAll() {
        return productService.findAll().stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable final Long id) {
        return ProductResponse.from(productService.findById(id));
    }

    @GetMapping("/findByIds")
    public List<ProductResponse> findByIds(@RequestParam final List<Long> ids) {
        return productService.findByIds(ids).stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    @PostMapping()
    public Product save(@RequestBody final Product product) {
        return productService.save(product);
    }
}
