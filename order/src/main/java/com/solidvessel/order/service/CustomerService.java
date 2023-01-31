package com.solidvessel.order.service;

import com.solidvessel.order.port.CustomerPort;
import com.solidvessel.order.response.CustomerResponse;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final CustomerPort customerPort;

    public CustomerService(final CircuitBreakerFactory circuitBreakerFactory, final CustomerPort customerPort) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.customerPort = customerPort;
    }

    public CustomerResponse getCustomerOfOrder(final Long customerId, final String session) {
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> customerPort.getById(customerId, session),
                        throwable -> new CustomerResponse(null, null, null, "Couldn't retrieve customer of the order."));
    }
}