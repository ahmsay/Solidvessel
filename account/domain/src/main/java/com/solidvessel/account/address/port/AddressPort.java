package com.solidvessel.account.address.port;

import com.solidvessel.account.address.model.Address;

public interface AddressPort {

    Address save(Address address);

    void delete(Long id, String customerId);
}
