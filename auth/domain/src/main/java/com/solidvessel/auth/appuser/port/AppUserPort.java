package com.solidvessel.auth.appuser.port;

import com.solidvessel.auth.appuser.model.AppUser;

public interface AppUserPort {

    Long save(final AppUser appUser);
}
