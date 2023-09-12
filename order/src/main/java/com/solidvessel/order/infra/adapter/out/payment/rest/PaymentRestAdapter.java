package com.solidvessel.order.infra.adapter.out.payment.rest;

import com.solidvessel.order.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.order.domain.payment.port.PaymentQueryPort;
import com.solidvessel.shared.infra.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRestAdapter implements PaymentQueryPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentRestClient paymentRestClient;

    @Override
    public PaymentDataModel getPaymentOfOrder(Long paymentId) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> paymentRestClient.getById(paymentId, session),
                        throwable -> null);
    }
}
