package com.solidvessel.payment.infra.adapter.product.rest;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import com.solidvessel.payment.domain.product.port.ProductsPort;
import com.solidvessel.shared.infra.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductRestAdapter implements ProductsPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final ProductRestClient productRestClient;

    @Override
    public List<ProductDataModel> getProductsOfPayment(Long paymentId) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("productCircuitBreaker")
                .run(() -> productRestClient.getByPaymentId(paymentId, session),
                        throwable -> new ArrayList<>());
    }
}
