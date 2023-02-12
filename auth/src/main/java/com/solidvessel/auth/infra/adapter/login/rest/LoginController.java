package com.solidvessel.auth.infra.adapter.login.rest;

import com.solidvessel.auth.domain.login.service.LoginCommandService;
import com.solidvessel.auth.infra.adapter.login.rest.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginCommandService loginCommandService;

    public LoginController(LoginCommandService loginCommandService) {
        this.loginCommandService = loginCommandService;
    }

    @PostMapping("/login")
    public void login(@RequestBody final LoginRequest loginRequest) {
        loginCommandService.login(loginRequest.toCommand());
    }
}
