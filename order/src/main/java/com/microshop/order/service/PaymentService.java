package com.microshop.order.service;

import com.microshop.order.port.PaymentPort;
import com.microshop.order.response.PaymentResponse;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentPort paymentPort;

    public PaymentService(final CircuitBreakerFactory circuitBreakerFactory, final PaymentPort paymentPort) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.paymentPort = paymentPort;
    }

    public PaymentResponse getPaymentOfOrder(final Long paymentId, final String session) {
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> paymentPort.getById(paymentId, session),
                        throwable -> new PaymentResponse(null, null, "Couldn't retrieve payment of the order."));
    }
}
