package com.solidvessel.account.adapter.out.customer.rest;

import com.solidvessel.account.customer.model.Customer;
import org.keycloak.representations.idm.UserRepresentation;

import java.time.LocalDate;

public class UserMapper {

    public static Customer mapToCustomer(UserRepresentation user) {
        var attributes = user.getAttributes();
        LocalDate birthDate = null;
        String phoneNumber = null;
        if (attributes != null) {
            var birthDateAttribute = attributes.get("birthDate");
            if (birthDateAttribute != null) {
                birthDate = LocalDate.parse(birthDateAttribute.get(0));
            }
            var phoneNumberAttribute = attributes.get("phoneNumber");
            if (phoneNumberAttribute != null) {
                phoneNumber = phoneNumberAttribute.get(0);
            }
        }

        return new Customer(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                birthDate,
                user.getEmail(),
                phoneNumber
        );
    }
}
