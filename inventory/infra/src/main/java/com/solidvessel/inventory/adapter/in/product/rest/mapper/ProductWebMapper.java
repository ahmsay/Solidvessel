package com.solidvessel.inventory.adapter.in.product.rest.mapper;

import com.solidvessel.inventory.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.inventory.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductWebMapper {

    ProductWebMapper INSTANCE = Mappers.getMapper(ProductWebMapper.class);

    ProductResponse toResponse(Product product);
}
