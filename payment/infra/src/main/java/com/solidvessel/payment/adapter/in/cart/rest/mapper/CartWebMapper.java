package com.solidvessel.payment.adapter.in.cart.rest.mapper;

import com.solidvessel.payment.adapter.in.cart.rest.response.CartResponse;
import com.solidvessel.payment.cart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CartWebMapper {

    CartWebMapper INSTANCE = Mappers.getMapper(CartWebMapper.class);

    CartResponse toResponse(Cart cart);
}
