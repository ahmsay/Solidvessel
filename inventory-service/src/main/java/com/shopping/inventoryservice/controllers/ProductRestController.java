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

    private final IProductService productService;
    private final IPaymentService paymentService;

    public ProductRestController(final IProductService productService, final IPaymentService paymentService) {
        this.productService = productService;
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") final Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/ofPayment/{paymentId}")
    public List<Product> getProductsOfPayment(@PathVariable("paymentId") final Long id) {
        return productService.getProductsOfPayment(id);
    }

    @GetMapping("/{productId}/payment")
    public Payment getPaymentOfProduct(@PathVariable("productId") final Long id) {
        return paymentService.getPaymentOfProduct(id);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody final Product product) {
        return productService.addProduct(product);
    }
}
