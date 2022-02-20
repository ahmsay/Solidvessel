package com.microshop.account.service;

import com.microshop.account.port.PaymentPort;
import com.microshop.account.response.PaymentsResponse;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PaymentService {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentPort paymentPort;

    public PaymentService(final CircuitBreakerFactory circuitBreakerFactory, final PaymentPort paymentPort) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.paymentPort = paymentPort;
    }

    public PaymentsResponse getPaymentsOfCustomer(final Long customerId) {
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> PaymentsResponse.from(paymentPort.getByCustomerId(customerId)),
                        throwable -> new PaymentsResponse(new ArrayList<>(), "Couldn't retrieve payments of the customer."));
    }
}
