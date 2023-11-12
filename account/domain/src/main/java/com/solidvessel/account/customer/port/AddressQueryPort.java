package com.solidvessel.account.customer.port;

import com.solidvessel.account.customer.datamodel.AddressDataModel;

import java.util.List;

public interface AddressQueryPort {

    List<AddressDataModel> getAddresses(String customerId);

    boolean isAddressRegistered(String customerId, String name);
}
