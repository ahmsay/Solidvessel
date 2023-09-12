package com.solidvessel.auth.appuser.port;

import com.solidvessel.auth.appuser.model.AppUser;

public interface AppUserQueryPort {

    AppUser getById(Long id);

    AppUser getByEmail(String email);

    boolean isEmailRegistered(String email);
}
