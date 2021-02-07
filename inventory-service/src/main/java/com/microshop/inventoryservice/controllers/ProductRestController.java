package com.microshop.inventoryservice.controllers;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.services.IProductService;
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
    public Product findById(@PathVariable final Long id) {
        return productService.findById(id);
    }

    @GetMapping("/findByIds")
    public Iterable<Product> findByIds(@RequestParam final List<Long> ids) {
        return productService.findByIds(ids);
    }

    @PostMapping()
    public Product save(@RequestBody final Product product) {
        return productService.save(product);
    }
}
