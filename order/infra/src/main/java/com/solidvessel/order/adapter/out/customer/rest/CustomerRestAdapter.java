package com.solidvessel.order.adapter.out.customer.rest;

import com.solidvessel.order.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.customer.port.CustomerQueryPort;
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
    public CustomerDataModel getCustomerOfOrder(String customerId) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> customerRestClient.getById(customerId, token),
                        throwable -> null);
    }
}
