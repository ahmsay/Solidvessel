package com.solidvessel.order.infra.adapter.payment.rest;

import com.solidvessel.order.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.order.domain.payment.port.PaymentPort;
import com.solidvessel.shared.infra.util.SessionUtil;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentRestAdapter implements PaymentPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentRestClient paymentRestClient;

    public PaymentRestAdapter(CircuitBreakerFactory circuitBreakerFactory, PaymentRestClient paymentRestClient) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.paymentRestClient = paymentRestClient;
    }

    @Override
    public PaymentDataModel getPaymentOfOrder(Long paymentId) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> paymentRestClient.getById(paymentId, session).data(),
                        throwable -> null);
    }
}
