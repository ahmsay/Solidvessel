package com.microshop.order.service;

import com.microshop.order.port.CustomerPort;
import com.microshop.order.response.CustomerResponse;
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

    public CustomerResponse getCustomerOfOrder(final Long customerId) {
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> customerPort.getById(customerId),
                        throwable -> new CustomerResponse(null, null, "Couldn't retrieve customer of the order."));
    }
}
