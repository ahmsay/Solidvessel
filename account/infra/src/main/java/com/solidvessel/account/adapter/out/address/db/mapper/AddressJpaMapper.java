package com.solidvessel.account.adapter.out.address.db.mapper;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.address.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressJpaMapper {

    AddressJpaEntity toJpaEntity(Address address);

    Address toDomainModel(AddressJpaEntity address);
}
