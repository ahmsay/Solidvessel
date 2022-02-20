package com.microshop.payment.service;

import com.microshop.payment.port.CustomerPort;
import com.microshop.payment.response.CustomerResponse;
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

    public CustomerResponse getCustomerOfPayment(final Long customerId) {
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> customerPort.getById(customerId),
                        throwable -> new CustomerResponse(null, null, "Couldn't retrieve customer of the payment."));
    }
}
