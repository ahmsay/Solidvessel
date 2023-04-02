package com.solidvessel.account.infra.adapter.customer.db;

import com.solidvessel.account.domain.customer.datamodel.AddressDataModel;
import com.solidvessel.account.domain.customer.model.Address;
import com.solidvessel.account.domain.customer.port.AddressPort;
import com.solidvessel.account.infra.adapter.customer.db.entity.AddressEmbeddable;
import com.solidvessel.account.infra.adapter.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.infra.adapter.customer.db.repository.CustomerRepository;
import com.solidvessel.shared.infra.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressDBAdapter implements AddressPort {

    private final CustomerDBAdapter customerDBAdapter;
    private final CustomerRepository customerRepository;

    @Override
    public List<AddressDataModel> getAddresses() {
        Long customerId = SessionUtil.getCurrentUserId();
        CustomerJpaEntity customer = customerDBAdapter.getCustomerById(customerId);
        return customer.getAddresses().stream().map(AddressEmbeddable::toDataModel).toList();
    }

    @Override
    public void addAddress(Long customerId, Address address) {
        CustomerJpaEntity customer = customerDBAdapter.getCustomerById(customerId);
        customer.addAddress(AddressEmbeddable.from(address));
        customerRepository.save(customer);
    }

    @Override
    public void removeAddress(Long customerId, String addressName) {
        CustomerJpaEntity customer = customerDBAdapter.getCustomerById(customerId);
        customer.removeAddress(addressName);
        customerRepository.save(customer);
    }

    @Override
    public boolean isAddressRegistered(Long customerId, String addressName) {
        CustomerJpaEntity customer = customerDBAdapter.getCustomerById(customerId);
        return customer.getAddresses().stream().anyMatch(address -> address.getName().equals(addressName));
    }
}
