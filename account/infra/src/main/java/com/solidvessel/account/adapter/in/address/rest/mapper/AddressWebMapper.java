package com.solidvessel.account.adapter.in.address.rest.mapper;

import com.solidvessel.account.adapter.in.address.rest.response.AddressResponse;
import com.solidvessel.account.address.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressWebMapper {

    AddressWebMapper INSTANCE = Mappers.getMapper(AddressWebMapper.class);

    AddressResponse toResponse(Address address);
}
