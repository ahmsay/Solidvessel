package com.solidvessel.payment.adapter.out.customer.db.mapper;

import com.solidvessel.payment.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.payment.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerJpaMapper {

    CustomerJpaEntity toJpaEntity(Customer customer);

    Customer toDomainModel(CustomerJpaEntity customer);
}
