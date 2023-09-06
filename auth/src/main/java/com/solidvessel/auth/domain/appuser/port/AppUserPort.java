package com.solidvessel.auth.domain.appuser.port;

import com.solidvessel.auth.domain.appuser.model.AppUser;

public interface AppUserPort {

    Long save(final AppUser appUser);
}
