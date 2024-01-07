package com.solidvessel.payment.adapter.out.customer.rest;

import com.solidvessel.payment.customer.model.Customer;
import org.keycloak.representations.idm.UserRepresentation;

public class UserMapper {

    public static Customer mapToCustomer(UserRepresentation user) {
        return new Customer(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
