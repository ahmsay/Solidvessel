package com.solidvessel.account.domain.appuser.event;

import java.time.LocalDate;

public record UserSavedEvent(Long userId, String firstName, String lastName, String email, LocalDate birthDate,
                             String phoneNumber) {
}
