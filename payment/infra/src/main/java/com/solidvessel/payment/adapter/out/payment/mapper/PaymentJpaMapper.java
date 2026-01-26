package com.solidvessel.payment.adapter.out.payment.mapper;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.product.db.mapper.ProductJpaMapper;
import com.solidvessel.payment.payment.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {ProductJpaMapper.class})
public interface PaymentJpaMapper {

    PaymentJpaEntity toJpaEntity(Payment payment);

    Payment toDomainModel(PaymentJpaEntity paymentJpaEntity);
}
