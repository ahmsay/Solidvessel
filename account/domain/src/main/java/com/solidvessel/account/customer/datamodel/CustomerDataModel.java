package com.solidvessel.account.customer.datamodel;

import java.time.LocalDate;

public record CustomerDataModel(Long id, String firstName, String lastName, LocalDate birthDate, String email,
                                String phoneNumber) {
}
