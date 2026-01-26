package com.solidvessel.account.adapter.in.customer.rest.response;

import java.time.LocalDate;

public record CustomerResponse(String id, String firstName, String lastName, LocalDate birthDate, String email,
                               String phoneNumber) {
}
