package com.solidvessel.account.adapter.out.customer.db;

import com.solidvessel.account.adapter.out.customer.db.entity.AddressEmbeddable;
import com.solidvessel.account.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.adapter.out.customer.db.repository.CustomerRepository;
import com.solidvessel.account.customer.model.Address;
import com.solidvessel.account.customer.port.AddressPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressDBAdapter implements AddressPort {

    private final CustomerDBQueryAdapter customerDBQueryAdapter;
    private final CustomerRepository customerRepository;

    @Override
    public void addAddress(String customerId, Address address) {
        CustomerJpaEntity customer = customerDBQueryAdapter.getCustomerById(customerId);
        customer.addAddress(AddressEmbeddable.from(address));
        customerRepository.save(customer);
    }

    @Override
    public void removeAddress(String customerId, String addressName) {
        CustomerJpaEntity customer = customerDBQueryAdapter.getCustomerById(customerId);
        customer.removeAddress(addressName);
        customerRepository.save(customer);
    }

    @Override
    public void updateAddress(String customerId, Address address) {
        CustomerJpaEntity customer = customerDBQueryAdapter.getCustomerById(customerId);
        customer.removeAddress(address.getName());
        customer.addAddress(AddressEmbeddable.from(address));
        customerRepository.save(customer);
    }
}
