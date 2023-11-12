package com.solidvessel.account.customer.port;

import com.solidvessel.account.customer.model.Address;

public interface AddressPort {

    void addAddress(String customerId, Address address);

    void removeAddress(String customerId, String name);

    void updateAddress(String customerId, Address address);
}
