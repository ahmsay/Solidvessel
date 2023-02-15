package com.solidvessel.account.infra.adapter.payment.rest;

import com.solidvessel.account.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.account.domain.payment.port.PaymentPort;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentRestAdapter implements PaymentPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentRestClient paymentRestClient;

    public PaymentRestAdapter(final CircuitBreakerFactory circuitBreakerFactory, final PaymentRestClient paymentRestClient) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.paymentRestClient = paymentRestClient;
    }

    public List<PaymentDataModel> getPaymentsOfCustomer(final Long customerId, final String session) {
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> paymentRestClient.getByCustomerId(customerId, session).data(),
                        throwable -> new ArrayList<>());
    }
}
