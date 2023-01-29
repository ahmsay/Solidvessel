package com.solidvessel.account.event;

public record UserSavedEvent(Long userId, String firstName, String lastName) {
}
