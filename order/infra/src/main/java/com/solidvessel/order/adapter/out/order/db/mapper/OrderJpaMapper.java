package com.solidvessel.order.adapter.out.order.db.mapper;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderJpaMapper {

    @Mapping(source = "cancellation.cancellationReason", target = "cancellationReason")
    @Mapping(source = "cancellation.explanation", target = "cancellationExplanation")
    OrderJpaEntity toJpaEntity(Order order);

    @Mapping(source = "cancellationReason", target = "cancellation.cancellationReason")
    @Mapping(source = "cancellationExplanation", target = "cancellation.explanation")
    Order toDomainModel(OrderJpaEntity entity);
}
