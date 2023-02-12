package com.solidvessel.auth.infra.adapter.signup.rest;

import com.solidvessel.auth.domain.signup.service.SignUpCommandService;
import com.solidvessel.auth.infra.adapter.signup.rest.request.SignUpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    private final SignUpCommandService signUpCommandService;

    public SignUpController(SignUpCommandService signUpCommandService) {
        this.signUpCommandService = signUpCommandService;
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody final SignUpRequest signUpRequest) {
        signUpCommandService.signUp(signUpRequest.toCommand());
    }
}
