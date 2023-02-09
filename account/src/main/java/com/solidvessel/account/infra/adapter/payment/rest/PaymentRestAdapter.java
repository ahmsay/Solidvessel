package com.solidvessel.account.infra.adapter.payment.rest;

import com.solidvessel.account.domain.payment.port.PaymentPort;
import com.solidvessel.account.infra.adapter.payment.rest.response.PaymentsResponse;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PaymentRestAdapter implements PaymentPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentRestClient paymentRestClient;

    public PaymentRestAdapter(final CircuitBreakerFactory circuitBreakerFactory, final PaymentRestClient paymentRestClient) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.paymentRestClient = paymentRestClient;
    }

    public PaymentsResponse getPaymentsOfCustomer(final Long customerId, final String session) {
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> PaymentsResponse.from(paymentRestClient.getByCustomerId(customerId, session)),
                        throwable -> new PaymentsResponse(new ArrayList<>(), "Couldn't retrieve payments of the customer."));
    }
}
