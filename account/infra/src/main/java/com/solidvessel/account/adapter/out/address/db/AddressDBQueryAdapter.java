package com.solidvessel.account.adapter.out.address.db;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.adapter.out.address.db.repository.AddressRepository;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressQueryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressDBQueryAdapter implements AddressQueryPort {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAddresses(String customerId) {
        return addressRepository.findByCustomerId(customerId).stream().map(AddressJpaEntity::toDomainModel).toList();
    }

    @Override
    public boolean isAddressRegistered(String customerId, String addressName) {
        return addressRepository.existsByCustomerIdAndName(customerId, addressName);
    }

    @Override
    public Address getByIdAndCustomerId(Long id, String customerId) {
        return addressRepository.findByIdAndCustomerId(id, customerId).map(AddressJpaEntity::toDomainModel).orElseThrow(() -> new EntityNotFoundException("Address not found!"));
    }
}
