package com.solidvessel.account.adapter.out.customer.rest;

import com.solidvessel.account.customer.datamodel.CustomerDataModel;
import org.keycloak.representations.idm.UserRepresentation;

import java.time.LocalDate;

public class UserMapper {

    public static CustomerDataModel mapToCustomer(UserRepresentation user) {
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

        return new CustomerDataModel(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                birthDate,
                user.getEmail(),
                phoneNumber
        );
    }
}
