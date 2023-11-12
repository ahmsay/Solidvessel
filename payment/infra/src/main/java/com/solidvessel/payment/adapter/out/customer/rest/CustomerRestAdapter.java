package com.solidvessel.payment.adapter.out.customer.rest;

import com.solidvessel.payment.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.customer.port.CustomerQueryPort;
import com.solidvessel.shared.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRestAdapter implements CustomerQueryPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final CustomerRestClient customerRestClient;

    @Override
    public CustomerDataModel getCustomerOfPayment(Long customerId) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> customerRestClient.getById(customerId, token),
                        throwable -> null);
    }
}
