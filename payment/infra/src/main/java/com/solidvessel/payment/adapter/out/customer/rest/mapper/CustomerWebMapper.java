package com.solidvessel.payment.adapter.out.customer.rest.mapper;

import com.solidvessel.payment.adapter.out.customer.rest.response.CustomerResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerWebMapper {

    CustomerResponse toResponse(UserRepresentation user);
}
