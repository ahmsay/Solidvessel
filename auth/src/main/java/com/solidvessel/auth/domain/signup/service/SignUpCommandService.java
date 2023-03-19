package com.solidvessel.auth.domain.signup.service;

import com.solidvessel.auth.domain.appuser.event.UserSavedEvent;
import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.auth.domain.appuser.port.UserSavedPort;
import com.solidvessel.auth.domain.signup.service.command.SignUpCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCommandService {

    private final AppUserPort appUserPort;
    private final UserSavedPort userSavedPort;

    public void signUp(SignUpCommand command) {
        Long userId = appUserPort.add(new AppUser(command.username(), command.password()));
        userSavedPort.sendUserSavedEvent(new UserSavedEvent(userId, command.firstName(), command.lastName()));
    }
}
