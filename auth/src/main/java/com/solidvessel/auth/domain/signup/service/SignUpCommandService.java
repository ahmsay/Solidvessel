package com.solidvessel.auth.domain.signup.service;

import com.solidvessel.auth.domain.appuser.event.UserSavedEvent;
import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.auth.domain.appuser.port.UserSavedPort;
import com.solidvessel.auth.domain.signup.service.command.SignUpCommand;
import org.springframework.stereotype.Service;

@Service
public class SignUpCommandService {

    private final AppUserPort appUserPort;
    private final UserSavedPort userSavedPort;

    public SignUpCommandService(AppUserPort appUserPort, UserSavedPort userSavedPort) {
        this.appUserPort = appUserPort;
        this.userSavedPort = userSavedPort;
    }

    public void signUp(SignUpCommand command) {
        Long userId = appUserPort.add(new AppUser(command.username(), command.password()));
        userSavedPort.sendUserSavedEvent(new UserSavedEvent(userId, command.firstName(), command.lastName()));
    }
}
