package com.solidvessel.account.adapter.out.address.db;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.adapter.out.address.db.mapper.AddressJpaMapper;
import com.solidvessel.account.adapter.out.address.db.repository.AddressRepository;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.address.port.AddressPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressDBAdapter implements AddressPort {

    private final AddressRepository addressRepository;
    private final AddressJpaMapper addressJpaMapper;

    @CacheEvict(value = "addresses", key = "#address.customerId")
    @Override
    public Address save(Address address) {
        AddressJpaEntity addressJpaEntity = addressRepository.save(addressJpaMapper.toJpaEntity(address));
        return addressJpaMapper.toDomainModel(addressJpaEntity);
    }

    @CacheEvict(value = "addresses", key = "#customerId")
    @Override
    public void delete(Long id, String customerId) {
        addressRepository.deleteById(id);
    }
}
