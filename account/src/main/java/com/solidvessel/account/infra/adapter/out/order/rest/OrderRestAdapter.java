package com.solidvessel.account.infra.adapter.out.order.rest;

import com.solidvessel.account.domain.order.datamodel.OrderDataModel;
import com.solidvessel.account.domain.order.port.OrderQueryPort;
import com.solidvessel.shared.infra.util.SessionUtil;
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
        String session = SessionUtil.getCurrentUserSession();
        return circuitBreakerFactory.create("orderCircuitBreaker")
                .run(() -> orderRestClient.getByCustomerId(customerId, session),
                        throwable -> new ArrayList<>());
    }
}
