package com.solidvessel.account.domain.appuser.event;

public record UserSavedEvent(Long userId, String firstName, String lastName) {
}
