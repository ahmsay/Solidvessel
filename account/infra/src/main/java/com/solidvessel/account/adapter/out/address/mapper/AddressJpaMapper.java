package com.solidvessel.account.adapter.out.address.mapper;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.address.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressJpaMapper {

    AddressJpaMapper INSTANCE = Mappers.getMapper(AddressJpaMapper.class);

    AddressJpaEntity toJpaEntity(Address address);

    Address toDomainModel(AddressJpaEntity address);
}
