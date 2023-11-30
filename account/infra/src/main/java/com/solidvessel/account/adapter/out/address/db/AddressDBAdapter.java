package com.solidvessel.account.adapter.out.address.db;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.adapter.out.address.db.repository.AddressRepository;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressDBAdapter implements AddressPort {

    private final AddressRepository addressRepository;

    @Override
    public void save(Address address) {
        addressRepository.save(AddressJpaEntity.from(address));
    }

    @Override
    public void delete(Long id, String customerId) {
        addressRepository.deleteByIdAndCustomerId(id, customerId);
    }
}
