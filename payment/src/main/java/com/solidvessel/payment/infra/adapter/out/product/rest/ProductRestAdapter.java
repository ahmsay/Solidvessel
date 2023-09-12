package com.solidvessel.payment.infra.adapter.out.product.rest;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import com.solidvessel.payment.domain.product.port.ProductQueryPort;
import com.solidvessel.shared.infra.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductRestAdapter implements ProductQueryPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final ProductRestClient productRestClient;

    @Override
    public List<ProductDataModel> getProductsOfCart(Set<Long> productIds) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("productCircuitBreaker")
                .run(() -> productRestClient.getByIds(productIds, session),
                        throwable -> new ArrayList<>());
    }

    @Override
    public boolean isAvailable(Long productId, int quantity) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("productCircuitBreaker")
                .run(() -> productRestClient.isAvailable(productId, quantity, session),
                        throwable -> false);
    }
}
