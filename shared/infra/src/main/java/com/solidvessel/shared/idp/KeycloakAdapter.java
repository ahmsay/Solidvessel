package com.solidvessel.shared.idp;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
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

    public void activateUser(String id) {
        keycloakRealm.users().get(id).toRepresentation().setEnabled(true);
    }

    public void deactivateUser(String id) {
        keycloakRealm.users().get(id).toRepresentation().setEnabled(false);
    }
}
