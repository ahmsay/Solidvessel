package com.solidvessel.inventory.adapter.out.product.mapper;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductJpaMapper {

    ProductJpaMapper INSTANCE = Mappers.getMapper(ProductJpaMapper.class);

    ProductJpaEntity toJpaEntity(Product product);

    Product toDomainModel(ProductJpaEntity product);
}
