package com.solidvessel.auth.domain.login.service;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.auth.domain.login.port.LoginPort;
import com.solidvessel.auth.domain.login.service.command.LoginCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginCommandService implements CommandService<LoginCommand> {

    private final AppUserPort appUserPort;
    private final LoginPort loginPort;

    @Override
    public OperationResult execute(LoginCommand command) {
        AppUser appUser = appUserPort.getByEmail(command.email());
        checkCredentials(appUser, command);
        loginPort.login(appUser.getId().toString());
        return new OperationResult("Login successful.", ResultType.SUCCESS);
    }

    private void checkCredentials(AppUser appUser, LoginCommand command) {
        if (!appUser.getPassword().equals(command.password())) {
            throw new RuntimeException("Invalid email or password!");
        }
    }
}
