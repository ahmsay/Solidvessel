package com.solidvessel.account.adapter.out.order.rest;

import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.order.model.Order;
import com.solidvessel.account.order.port.OrderQueryPort;
import com.solidvessel.shared.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderRestAdapter implements OrderQueryPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final OrderRestClient orderRestClient;

    public List<Order> getOrdersOfCustomer(final String customerId) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("orderCircuitBreaker")
                .run(() -> orderRestClient.getByCustomerId(customerId, token).stream().map(OrderResponse::toDomainModel).toList(),
                        throwable -> new ArrayList<>());
    }
}
