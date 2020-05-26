package com.shopping.inventoryservice.controllers;

import com.shopping.inventoryservice.entity.Payment;
import com.shopping.inventoryservice.entity.Product;
import com.shopping.inventoryservice.services.IPaymentRemoteService;
import com.shopping.inventoryservice.services.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private IProductService productService;
    private IPaymentRemoteService paymentRemoteService;

    public ProductRestController(final IProductService productService, final IPaymentRemoteService paymentRemoteService) {
        this.productService = productService;
        this.paymentRemoteService = paymentRemoteService;
    }

    @GetMapping("/")
    public Set<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") final String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/withIds")
    public List<Product> getProductsByIds(@RequestParam("productIds") final List<String> productIds) {
        return productService.getProductsByIds(productIds);
    }

    @GetMapping("/{productId}/payment")
    public Payment getPaymentOfProduct(@PathVariable("productId") final String id) {
        return paymentRemoteService.getPaymentOfProduct(id);
    }
}
