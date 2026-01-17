package com.solidvessel.account.adapter.in.address.rest.mapper;

import com.solidvessel.account.adapter.in.address.rest.response.AddressResponse;
import com.solidvessel.account.address.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressWebMapper {

    AddressResponse toResponse(Address address);
}
