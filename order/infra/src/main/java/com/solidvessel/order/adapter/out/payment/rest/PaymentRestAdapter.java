package com.solidvessel.order.adapter.out.payment.rest;

import com.solidvessel.order.payment.model.Payment;
import com.solidvessel.order.payment.port.PaymentQueryPort;
import com.solidvessel.shared.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRestAdapter implements PaymentQueryPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentRestClient paymentRestClient;

    @Override
    public Payment getPaymentOfOrder(Long paymentId) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> paymentRestClient.getById(paymentId, token).toDomainModel(),
                        throwable -> null);
    }
}
