package com.solidvessel.account.domain.customer.port;

import com.solidvessel.account.domain.customer.datamodel.AddressDataModel;
import com.solidvessel.account.domain.customer.model.Address;

import java.util.List;

public interface AddressPort {

    List<AddressDataModel> getAddresses();

    void addAddress(Long customerId, Address address);

    void removeAddress(Long customerId, String name);

    boolean isAddressRegistered(Long customerId, String name);
}
