package com.solidvessel.account.adapter.out.address.db;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.adapter.out.address.db.repository.AddressRepository;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressQueryPort;
import com.solidvessel.shared.query.QueryOptions;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.solidvessel.shared.jpa.query.PageUtil.withPage;

@Repository
@RequiredArgsConstructor
public class AddressDBQueryAdapter implements AddressQueryPort {

    private final AddressRepository addressRepository;

    @Cacheable(value = "addresses", key = "#customerId")
    @Override
    public List<Address> getAddresses(String customerId, QueryOptions queryOptions) {
        return addressRepository.findByCustomerId(customerId, withPage(queryOptions)).stream().map(AddressJpaEntity::toDomainModel).toList();
    }

    @Override
    public boolean isAddressRegistered(Long id, String customerId) {
        return addressRepository.existsByIdAndCustomerId(id, customerId);
    }

    @Override
    public boolean isAddressRegistered(String customerId, String name) {
        return addressRepository.existsByNameAndCustomerId(name, customerId);
    }

    @Override
    public Address getByIdAndCustomerId(Long id, String customerId) {
        return addressRepository.findByIdAndCustomerId(id, customerId).map(AddressJpaEntity::toDomainModel).orElseThrow(() -> new EntityNotFoundException("Address not found!"));
    }

    @Override
    public int getAddressCount(String customerId) {
        return addressRepository.countByCustomerId(customerId);
    }

    @Override
    public Address getPrimaryAddress(String customerId) {
        return addressRepository.findByCustomerIdAndIsPrimaryIsTrue(customerId).map(AddressJpaEntity::toDomainModel).orElseThrow(() -> new EntityNotFoundException("Address not found!"));
    }
}
