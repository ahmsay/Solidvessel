package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.entity.Payment;
import com.shopping.inventoryservice.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentRemoteService implements IPaymentRemoteService {

    @Value("${paymentServiceUrl}")
    private String paymentServiceUrl;

    private RestTemplate restTemplate;
    private IProductService productService;

    public PaymentRemoteService(final RestTemplate restTemplate, final IProductService productService) {
        this.restTemplate = restTemplate;
        this.productService = productService;
    }

    @Override
    public Payment getPaymentOfProduct(final String productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return null;
        }
        return restTemplate.getForObject(paymentServiceUrl + "/payments/" + product.getPaymentId(), Payment.class);
    }
}
