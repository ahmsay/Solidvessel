package com.solidvessel.order.adapter.out.customer.rest;

import com.solidvessel.order.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.customer.port.CustomerQueryPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRestAdapter implements CustomerQueryPort {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final RealmResource realm;

    @Override
    public CustomerDataModel getCustomerOfOrder(String customerId) {
        return circuitBreakerFactory.create("customerCircuitBreaker")
                .run(() -> {
                    UserRepresentation user = realm.users().get(customerId).toRepresentation();
                    return UserMapper.mapToCustomer(user);
                }, throwable -> null);
    }
}
