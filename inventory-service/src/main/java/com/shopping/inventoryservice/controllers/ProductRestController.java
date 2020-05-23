package com.shopping.inventoryservice.controllers;

import com.shopping.inventoryservice.entity.Product;
import com.shopping.inventoryservice.services.IProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private IProductService productService;

    public ProductRestController(final IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public Set<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") final String id) {
        return productService.getProductById(id);
    }
}
