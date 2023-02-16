package com.solidvessel.payment.infra.adapter.customer.rest;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.domain.customer.port.CustomerPort;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestAdapter implements CustomerPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final CustomerRestClient customerRestClient;

    public CustomerRestAdapter(CircuitBreakerFactory circuitBreakerFactory, CustomerRestClient customerRestClient) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public CustomerDataModel getCustomerOfPayment(Long customerId, String session) {
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> customerRestClient.getById(customerId, session).data(),
                        throwable -> null);
    }
}
