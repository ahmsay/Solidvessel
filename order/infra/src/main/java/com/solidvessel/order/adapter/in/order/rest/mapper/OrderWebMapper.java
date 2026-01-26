package com.solidvessel.order.adapter.in.order.rest.mapper;

import com.solidvessel.order.adapter.in.order.rest.response.OrderResponse;
import com.solidvessel.order.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderWebMapper {

    OrderResponse toResponse(Order order);
}
