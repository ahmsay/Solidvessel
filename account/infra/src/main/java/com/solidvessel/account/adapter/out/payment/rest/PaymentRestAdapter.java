package com.solidvessel.account.adapter.out.payment.rest;

import com.solidvessel.account.payment.datamodel.PaymentDataModel;
import com.solidvessel.account.payment.port.PaymentQueryPort;
import com.solidvessel.shared.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentRestAdapter implements PaymentQueryPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentRestClient paymentRestClient;

    public List<PaymentDataModel> getPaymentsOfCustomer(final String customerId) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> paymentRestClient.getByCustomerId(customerId, token),
                        throwable -> new ArrayList<>());
    }
}
