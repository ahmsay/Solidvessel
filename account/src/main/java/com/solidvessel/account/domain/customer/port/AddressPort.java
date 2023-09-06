package com.solidvessel.account.domain.customer.port;

import com.solidvessel.account.domain.customer.model.Address;

public interface AddressPort {

    void addAddress(Long customerId, Address address);

    void removeAddress(Long customerId, String name);

    void updateAddress(Long customerId, Address address);
}
