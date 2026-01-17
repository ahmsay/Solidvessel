package com.solidvessel.account.adapter.in.customer.rest.mapper;

import com.solidvessel.account.adapter.in.customer.rest.response.CustomerDetailResponse;
import com.solidvessel.account.adapter.in.customer.rest.response.CustomerResponse;
import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerWebMapper {

    CustomerWebMapper INSTANCE = Mappers.getMapper(CustomerWebMapper.class);

    @Mapping(source = "attributes", target = "birthDate")
    @Mapping(source = "attributes", target = "phoneNumber")
    CustomerResponse toCustomerResponse(UserRepresentation user);

    CustomerDetailResponse toCustomerDetailResponse(CustomerResponse customer, List<OrderResponse> orders, List<PaymentResponse> payments);

    default LocalDate birthDateFromAttributes(Map<String, List<String>> attributes) {
        if (attributes == null) {
            return null;
        }
        var value = attributes.get("birthDate");
        return value == null ? null : LocalDate.parse(value.getFirst());
    }

    default String phoneFromAttributes(Map<String, List<String>> attributes) {
        if (attributes == null) {
            return null;
        }
        var value = attributes.get("phoneNumber");
        return value == null ? null : value.getFirst();
    }
}
