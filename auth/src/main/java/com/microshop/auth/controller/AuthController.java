package com.microshop.auth.controller;

import com.microshop.auth.service.AuthService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/{id}")
    public void login(@PathVariable final Long id) {
        authService.login(id);
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
