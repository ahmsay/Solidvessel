package com.microshop.payment.service;

import com.microshop.payment.port.ProductPort;
import com.microshop.payment.response.ProductsResponse;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final ProductPort productPort;

    public ProductService(final CircuitBreakerFactory circuitBreakerFactory, final ProductPort productPort) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.productPort = productPort;
    }

    public ProductsResponse getProductsOfPayment(final Long paymentId) {
        return circuitBreakerFactory.create("productCircuitBreaker")
                .run(() -> ProductsResponse.from(productPort.getByPaymentId(paymentId)),
                        throwable -> new ProductsResponse(new ArrayList<>(), "Couldn't retrieve products of the payment."));
    }
}
