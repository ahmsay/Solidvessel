package com.solidvessel.payment.adapter.out.customer.rest;

import com.solidvessel.payment.customer.datamodel.CustomerDataModel;
import org.keycloak.representations.idm.UserRepresentation;

public class UserMapper {

    public static CustomerDataModel mapToCustomer(UserRepresentation user) {
        return new CustomerDataModel(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
