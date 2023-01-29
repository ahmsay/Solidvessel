package com.solidvessel.payment.service;

import com.solidvessel.payment.port.ProductPort;
import com.solidvessel.payment.response.ProductsResponse;
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

    public ProductsResponse getProductsOfPayment(final Long paymentId, final String session) {
        return circuitBreakerFactory.create("productCircuitBreaker")
                .run(() -> ProductsResponse.from(productPort.getByPaymentId(paymentId, session)),
                        throwable -> new ProductsResponse(new ArrayList<>(), "Couldn't retrieve products of the payment."));
    }
}
