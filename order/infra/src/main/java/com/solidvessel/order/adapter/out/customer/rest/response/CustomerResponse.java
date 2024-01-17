package com.solidvessel.order.adapter.out.customer.rest.response;

import org.keycloak.representations.idm.UserRepresentation;

public record CustomerResponse(String id, String firstName, String lastName) {

    public static CustomerResponse from(UserRepresentation user) {
        return new CustomerResponse(user.getId(), user.getFirstName(), user.getLastName());
    }
}
