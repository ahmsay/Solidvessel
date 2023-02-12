package com.solidvessel.auth.domain.appuser.port;

import com.solidvessel.auth.domain.appuser.event.UserSavedEvent;

public interface UserSavedPort {

    void sendUserSavedEvent(final UserSavedEvent event);
}
