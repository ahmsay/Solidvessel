package com.solidvessel.payment.infra.adapter.customer.rest;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.domain.customer.port.CustomerPort;
import com.solidvessel.shared.infra.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRestAdapter implements CustomerPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final CustomerRestClient customerRestClient;

    @Override
    public CustomerDataModel getCustomerOfPayment(Long customerId) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> customerRestClient.getById(customerId, session),
                        throwable -> null);
    }
}
