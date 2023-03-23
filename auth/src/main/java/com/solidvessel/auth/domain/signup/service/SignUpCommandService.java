package com.solidvessel.auth.domain.signup.service;

import com.solidvessel.auth.domain.appuser.event.UserSavedEvent;
import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.auth.domain.appuser.port.UserSavedPort;
import com.solidvessel.auth.domain.signup.service.command.SignUpCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCommandService implements CommandService<SignUpCommand> {

    private final AppUserPort appUserPort;
    private final UserSavedPort userSavedPort;

    @Override
    public OperationResult execute(SignUpCommand command) {
        if (appUserPort.isEmailRegistered(command.email())) {
            return new OperationResult("Email is already registered.", ResultType.ERROR);
        }
        Long userId = appUserPort.add(AppUser.newAppUser(command.email(), command.password()));
        userSavedPort.sendUserSavedEvent(new UserSavedEvent(userId, command.firstName(), command.lastName(), command.email()));
        return new OperationResult("Your registration is successful.", ResultType.SUCCESS);
    }
}
