package com.solidvessel.shared.idp;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakAdapter {

    private final RealmResource keycloakRealm;

    public List<UserRepresentation> getUsers(Integer start, Integer end) {
        return keycloakRealm.users().list(start, end);
    }

    public UserRepresentation getUser(String id) {
        return keycloakRealm.users().get(id).toRepresentation();
    }
}
