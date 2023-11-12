package com.solidvessel.account.adapter.out.order.rest;

import com.solidvessel.account.order.datamodel.OrderDataModel;
import com.solidvessel.account.order.port.OrderQueryPort;
import com.solidvessel.shared.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderRestAdapter implements OrderQueryPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final OrderRestClient orderRestClient;

    public List<OrderDataModel> getOrdersOfCustomer(final Long customerId) {
        String token = SessionUtil.getCurrentUserToken();
        return circuitBreakerFactory.create("orderCircuitBreaker")
                .run(() -> orderRestClient.getByCustomerId(customerId, token),
                        throwable -> new ArrayList<>());
    }
}
