package com.solidvessel.auth.event;

public record UserSavedEvent(Long userId, String firstName, String lastName) {
}
