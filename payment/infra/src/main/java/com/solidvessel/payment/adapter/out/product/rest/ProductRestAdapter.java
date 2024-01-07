package com.solidvessel.payment.adapter.out.product.rest;

import com.solidvessel.payment.adapter.out.product.rest.response.ProductResponse;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.port.ProductQueryPort;
import com.solidvessel.shared.security.SessionUtil;
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
    public List<Product> getProductsOfCart(Set<Long> productIds) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("productCircuitBreaker")
                .run(() -> productRestClient.getByIds(productIds, token).stream().map(ProductResponse::toDomainModel).toList(),
                        throwable -> new ArrayList<>());
    }

    @Override
    public boolean isAvailable(Long productId, int quantity) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("productCircuitBreaker")
                .run(() -> productRestClient.isAvailable(productId, quantity, token),
                        throwable -> false);
    }
}
