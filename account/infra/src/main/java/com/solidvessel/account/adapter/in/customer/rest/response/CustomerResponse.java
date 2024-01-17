package com.solidvessel.account.adapter.in.customer.rest.response;

import org.keycloak.representations.idm.UserRepresentation;

import java.time.LocalDate;

public record CustomerResponse(String id, String firstName, String lastName, LocalDate birthDate, String email,
                               String phoneNumber) {

    public static CustomerResponse from(UserRepresentation user) {
        var attributes = user.getAttributes();
        LocalDate birthDate = null;
        String phoneNumber = null;
        if (attributes != null) {
            var birthDateAttribute = attributes.get("birthDate");
            if (birthDateAttribute != null) {
                birthDate = LocalDate.parse(birthDateAttribute.getFirst());
            }
            var phoneNumberAttribute = attributes.get("phoneNumber");
            if (phoneNumberAttribute != null) {
                phoneNumber = phoneNumberAttribute.getFirst();
            }
        }

        return new CustomerResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                birthDate,
                user.getEmail(),
                phoneNumber
        );
    }
}
