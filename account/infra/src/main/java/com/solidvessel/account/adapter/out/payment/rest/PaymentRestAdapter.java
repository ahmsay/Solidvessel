package com.solidvessel.account.adapter.out.payment.rest;

import com.solidvessel.account.adapter.out.payment.rest.datamodel.PaymentDataModel;
import com.solidvessel.account.payment.model.Payment;
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

    public List<Payment> getPaymentsOfCustomer(final String customerId) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("paymentCircuitBreaker")
                .run(() -> paymentRestClient.getByCustomerId(customerId, token).stream().map(PaymentDataModel::toDomainModel).toList(),
                        throwable -> new ArrayList<>());
    }
}
