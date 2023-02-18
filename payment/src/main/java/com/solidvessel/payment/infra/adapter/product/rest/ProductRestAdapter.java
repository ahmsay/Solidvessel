package com.solidvessel.payment.infra.adapter.product.rest;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import com.solidvessel.payment.domain.product.port.ProductPort;
import com.solidvessel.shared.infra.util.SessionUtil;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRestAdapter implements ProductPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final ProductRestClient productRestClient;

    public ProductRestAdapter(CircuitBreakerFactory circuitBreakerFactory, ProductRestClient productRestClient) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.productRestClient = productRestClient;
    }

    @Override
    public List<ProductDataModel> getProductsOfPayment(Long paymentId) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("productCircuitBreaker")
                .run(() -> productRestClient.getByPaymentId(paymentId, session).data(),
                        throwable -> new ArrayList<>());
    }
}
