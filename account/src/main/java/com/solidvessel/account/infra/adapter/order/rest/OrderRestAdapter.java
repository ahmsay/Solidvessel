package com.solidvessel.account.infra.adapter.order.rest;

import com.solidvessel.account.domain.order.datamodel.OrderDataModel;
import com.solidvessel.account.domain.order.port.OrderPort;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRestAdapter implements OrderPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final OrderRestClient orderRestClient;

    public OrderRestAdapter(final CircuitBreakerFactory circuitBreakerFactory, final OrderRestClient orderRestClient) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.orderRestClient = orderRestClient;
    }

    public List<OrderDataModel> getOrdersOfCustomer(final Long customerId, final String session) {
        return circuitBreakerFactory.create("orderCircuitBreaker")
                .run(() -> orderRestClient.getByCustomerId(customerId, session).data(),
                        throwable -> new ArrayList<>());
    }
}
