package com.solidvessel.auth.controller;

import com.solidvessel.auth.entity.AppUser;
import com.solidvessel.auth.entity.SignUpInfo;
import com.solidvessel.auth.service.AuthService;
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
    public void signUp(@RequestBody final SignUpInfo signUpInfo) {
        authService.signUp(signUpInfo);
    }
}
