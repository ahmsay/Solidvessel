package com.solidvessel.payment.adapter.out.cart.db.mapper;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.cart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CartJpaMapper {

    CartJpaMapper INSTANCE = Mappers.getMapper(CartJpaMapper.class);

    CartJpaEntity toJpaEntity(Cart cart);

    Cart toDomainModel(CartJpaEntity cart);
}
