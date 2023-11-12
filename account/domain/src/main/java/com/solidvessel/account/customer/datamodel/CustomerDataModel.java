package com.solidvessel.account.customer.datamodel;

import java.time.LocalDate;

public record CustomerDataModel(String id, String firstName, String lastName, LocalDate birthDate, String email,
                                String phoneNumber) {
}
