package com.shopping.inventoryservice.controllers;

import com.shopping.inventoryservice.entity.Payment;
import com.shopping.inventoryservice.entity.Product;
import com.shopping.inventoryservice.services.IPaymentService;
import com.shopping.inventoryservice.services.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private IProductService productService;
    private IPaymentService paymentService;

    public ProductRestController(final IProductService productService, final IPaymentService paymentService) {
        this.productService = productService;
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") final String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/filter")
    public List<Product> getProductsByIds(@RequestParam("productIds") final List<String> productIds) {
        return productService.getProductsByIds(productIds);
    }

    @GetMapping("/{productId}/payment")
    public Payment getPaymentOfProduct(@PathVariable("productId") final String id) {
        return paymentService.getPaymentOfProduct(id);
    }
}
