package com.microshop.auth.event;

public record UserSavedEvent(Long userId, String firstName, String lastName) {
}
