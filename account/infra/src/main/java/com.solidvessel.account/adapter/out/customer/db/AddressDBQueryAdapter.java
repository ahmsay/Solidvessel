package com.solidvessel.account.adapter.out.customer.db;

import com.solidvessel.account.adapter.out.customer.db.entity.AddressEmbeddable;
import com.solidvessel.account.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.customer.datamodel.AddressDataModel;
import com.solidvessel.account.customer.port.AddressQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressDBQueryAdapter implements AddressQueryPort {

    private final CustomerDBQueryAdapter customerDBQueryAdapter;

    @Override
    public List<AddressDataModel> getAddresses(Long customerId) {
        CustomerJpaEntity customer = customerDBQueryAdapter.getCustomerById(customerId);
        return customer.getAddresses().stream().map(AddressEmbeddable::toDataModel).toList();
    }

    @Override
    public boolean isAddressRegistered(Long customerId, String addressName) {
        CustomerJpaEntity customer = customerDBQueryAdapter.getCustomerById(customerId);
        return customer.getAddresses().stream().anyMatch(address -> address.getName().equals(addressName));
    }
}
