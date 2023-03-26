package com.solidvessel.account.infra.adapter.payment.rest;

import com.solidvessel.account.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.account.domain.payment.port.PaymentPort;
import com.solidvessel.shared.infra.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentRestAdapter implements PaymentPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentRestClient paymentRestClient;

    public List<PaymentDataModel> getPaymentsOfCustomer(final Long customerId) {
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> paymentRestClient.getByCustomerId(customerId, session),
                        throwable -> new ArrayList<>());
    }
}
