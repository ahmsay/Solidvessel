package com.solidvessel.shared.idp;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakAdapter {

    private final RealmResource keycloakRealm;

    @Cacheable(value = "users")
    public List<UserRepresentation> getUsers(Integer start, Integer end) {
        return keycloakRealm.users().list(start, end);
    }

    @Cacheable(value = "user", key = "#id")
    public UserRepresentation getUser(String id) {
        return keycloakRealm.users().get(id).toRepresentation();
    }

    @CacheEvict(value = "user", key = "#id")
    public void activateUser(String id) {
        var user = keycloakRealm.users().get(id).toRepresentation();
        user.setEnabled(true);
        keycloakRealm.users().get(id).update(user);
    }

    @CacheEvict(value = "user", key = "#id")
    public void deactivateUser(String id) {
        var user = keycloakRealm.users().get(id).toRepresentation();
        user.setEnabled(false);
        keycloakRealm.users().get(id).update(user);
    }
}
