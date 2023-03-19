package com.solidvessel.auth.infra.adapter.signup.rest;

import com.solidvessel.auth.domain.signup.service.SignUpCommandService;
import com.solidvessel.auth.infra.adapter.signup.rest.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpCommandService signUpCommandService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody final SignUpRequest signUpRequest) {
        signUpCommandService.signUp(signUpRequest.toCommand());
    }
}
