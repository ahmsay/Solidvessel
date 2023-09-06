package com.solidvessel.account.domain.customer.port;

import com.solidvessel.account.domain.customer.datamodel.AddressDataModel;

import java.util.List;

public interface AddressQueryPort {

    List<AddressDataModel> getAddresses();

    boolean isAddressRegistered(Long customerId, String name);
}
