package com.solidvessel.account.adapter.out.customer.rest;

import com.solidvessel.account.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.customer.port.CustomerQueryPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerRestAdapter implements CustomerQueryPort {

    private final RealmResource realm;

    @Override
    public List<CustomerDataModel> getAll() {
        List<UserRepresentation> users = realm.users().list();
        return users.stream().map(UserMapper::mapToCustomer).toList();
    }

    @Override
    public CustomerDataModel getById(String id) {
        UserRepresentation user = realm.users().get(id).toRepresentation();
        return UserMapper.mapToCustomer(user);
    }
}
