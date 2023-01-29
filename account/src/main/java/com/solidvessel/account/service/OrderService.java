package com.solidvessel.account.service;

import com.solidvessel.account.port.OrderPort;
import com.solidvessel.account.response.OrdersResponse;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final OrderPort orderPort;

    public OrderService(final CircuitBreakerFactory circuitBreakerFactory, final OrderPort orderPort) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.orderPort = orderPort;
    }

    public OrdersResponse getOrdersOfCustomer(final Long customerId, final String session) {
        return circuitBreakerFactory.create("orderCircuitBreaker")
                .run(() -> OrdersResponse.from(orderPort.getByCustomerId(customerId, session)),
                        throwable -> new OrdersResponse(new ArrayList<>(), "Couldn't retrieve orders of the customer."));
    }
}
