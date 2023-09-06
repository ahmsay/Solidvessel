package com.solidvessel.auth.domain.login.service;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserQueryPort;
import com.solidvessel.auth.domain.common.exception.AuthDomainException;
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

    private final AppUserQueryPort appUserQueryPort;
    private final LoginPort loginPort;

    @Override
    public OperationResult execute(LoginCommand command) {
        AppUser appUser = appUserQueryPort.getByEmail(command.email());
        checkCredentials(appUser, command);
        loginPort.login(appUser.getId().toString());
        return new OperationResult("Login successful.", ResultType.SUCCESS);
    }

    private void checkCredentials(AppUser appUser, LoginCommand command) {
        if (!appUser.getPassword().equals(command.password())) {
            throw new AuthDomainException("Invalid email or password!");
        }
    }
}
