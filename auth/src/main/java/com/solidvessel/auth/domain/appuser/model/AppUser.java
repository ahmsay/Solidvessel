package com.solidvessel.auth.domain.appuser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppUser {

    private Long id;
    private String email;
    private String password;
    private Activity activity;

    public static AppUser newAppUser(String email, String password) {
        return new AppUser(null, email, password, new Activity(true, null));
    }
}
