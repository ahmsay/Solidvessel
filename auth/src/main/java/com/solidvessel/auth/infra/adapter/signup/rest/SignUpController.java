package com.solidvessel.auth.infra.adapter.signup.rest;

import com.solidvessel.auth.domain.signup.service.command.SignUpCommand;
import com.solidvessel.auth.infra.adapter.signup.rest.request.SignUpRequest;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final CommandService<SignUpCommand> signUpCommandService;

    @PostMapping("/signUp")
    public OperationResult signUp(@RequestBody @Valid final SignUpRequest signUpRequest) {
        return signUpCommandService.execute(signUpRequest.toCommand());
    }
}
