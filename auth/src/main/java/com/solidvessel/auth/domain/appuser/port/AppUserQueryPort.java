package com.solidvessel.auth.domain.appuser.port;

import com.solidvessel.auth.domain.appuser.model.AppUser;

public interface AppUserQueryPort {

    AppUser getById(Long id);

    AppUser getByEmail(String email);

    boolean isEmailRegistered(String email);
}
