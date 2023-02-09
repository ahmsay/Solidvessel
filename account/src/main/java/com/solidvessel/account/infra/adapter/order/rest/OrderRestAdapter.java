package com.solidvessel.account.infra.adapter.order.rest;

import com.solidvessel.account.domain.order.port.OrderPort;
import com.solidvessel.account.infra.adapter.order.rest.response.OrdersResponse;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderRestAdapter implements OrderPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final OrderRestClient orderRestClient;

    public OrderRestAdapter(final CircuitBreakerFactory circuitBreakerFactory, final OrderRestClient orderRestClient) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.orderRestClient = orderRestClient;
    }

    public OrdersResponse getOrdersOfCustomer(final Long customerId, final String session) {
        return circuitBreakerFactory.create("orderCircuitBreaker")
                .run(() -> OrdersResponse.from(orderRestClient.getByCustomerId(customerId, session)),
                        throwable -> new OrdersResponse(new ArrayList<>(), "Couldn't retrieve orders of the customer."));
    }
}
