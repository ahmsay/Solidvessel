package com.solidvessel.account.address.port;

import com.solidvessel.account.address.model.Address;

import java.util.List;

public interface AddressQueryPort {

    List<Address> getAddresses(String customerId);

    boolean isAddressRegistered(Long id, String customerId);

    boolean isAddressRegistered(String customerId, String name);

    Address getByIdAndCustomerId(Long id, String customerId);
}
