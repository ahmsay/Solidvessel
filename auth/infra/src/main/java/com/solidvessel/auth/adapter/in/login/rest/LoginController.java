package com.solidvessel.auth.adapter.in.login.rest;

import com.solidvessel.auth.adapter.in.login.rest.request.LoginRequest;
import com.solidvessel.auth.login.service.command.LoginCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final CommandService<LoginCommand> loginCommandService;

    @PostMapping("/login")
    public OperationResult login(@RequestBody @Valid final LoginRequest loginRequest) {
        return loginCommandService.execute(loginRequest.toCommand());
    }
}
