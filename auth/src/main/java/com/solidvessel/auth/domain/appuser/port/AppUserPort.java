package com.solidvessel.auth.domain.appuser.port;

import com.solidvessel.auth.domain.appuser.model.AppUser;

public interface AppUserPort {

    AppUser getById(final Long id);

    AppUser getByUsername(final String userName);

    Long add(final AppUser appUser);
}
