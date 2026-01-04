package com.solidvessel.account.adapter.in.customer.rest.response;

import com.solidvessel.account.adapter.in.customer.mapper.CustomerWebMapper;
import org.keycloak.representations.idm.UserRepresentation;

import java.time.LocalDate;

public record CustomerResponse(String id, String firstName, String lastName, LocalDate birthDate, String email,
                               String phoneNumber) {

    public static CustomerResponse from(UserRepresentation user) {
        return CustomerWebMapper.INSTANCE.toCustomerResponse(user);
    }
}
