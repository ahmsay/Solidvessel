package com.solidvessel.account.address.port;

import com.solidvessel.account.address.model.Address;
import com.solidvessel.shared.query.QueryOptions;

import java.util.List;

public interface AddressQueryPort {

    List<Address> getAddresses(String customerId, QueryOptions queryOptions);

    boolean isAddressRegistered(Long id, String customerId);

    boolean isAddressRegistered(String customerId, String name);

    Address getByIdAndCustomerId(Long id, String customerId);

    int getAddressCount(String customerId);

    Address getPrimaryAddress(String customerId);
}
