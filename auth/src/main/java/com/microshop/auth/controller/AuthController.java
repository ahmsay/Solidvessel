package com.microshop.auth.controller;

import com.microshop.auth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login/{id}")
    public void login(@PathVariable final Long id) {
        authService.login(id);
    }

    @GetMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
