package com.microshop.account.service;

import com.microshop.account.port.OrderPort;
import com.microshop.account.response.OrdersResponse;
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

    public OrdersResponse getOrdersOfCustomer(final Long customerId) {
        return circuitBreakerFactory.create("orderCircuitBreaker")
                .run(() -> OrdersResponse.from(orderPort.getByCustomerId(customerId)),
                        throwable -> new OrdersResponse(new ArrayList<>(), "Couldn't retrieve orders of the customer."));
    }
}
