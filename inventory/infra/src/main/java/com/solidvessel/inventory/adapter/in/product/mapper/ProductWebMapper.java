package com.solidvessel.inventory.adapter.in.product.mapper;

import com.solidvessel.inventory.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.inventory.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductWebMapper {

    ProductWebMapper INSTANCE = Mappers.getMapper(ProductWebMapper.class);

    ProductResponse toResponse(Product product);
}
