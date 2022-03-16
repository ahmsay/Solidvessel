package com.microshop.auth.controller;

import com.microshop.auth.entity.AppUser;
import com.microshop.auth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public void login(@RequestBody final AppUser loginRequest) {
        authService.login(loginRequest);
    }

    @GetMapping("/logout")
    public void logout() {
        authService.logout();
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody final AppUser signUpRequest) {
        authService.signUp(signUpRequest);
    }
}
