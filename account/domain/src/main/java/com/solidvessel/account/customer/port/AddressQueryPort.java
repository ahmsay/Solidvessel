package com.solidvessel.account.customer.port;

import com.solidvessel.account.customer.datamodel.AddressDataModel;

import java.util.List;

public interface AddressQueryPort {

    List<AddressDataModel> getAddresses(Long customerId);

    boolean isAddressRegistered(Long customerId, String name);
}
