package com.solidvessel.auth.domain.appuser.event;

public record UserSavedEvent(Long userId, String firstName, String lastName) {
}
