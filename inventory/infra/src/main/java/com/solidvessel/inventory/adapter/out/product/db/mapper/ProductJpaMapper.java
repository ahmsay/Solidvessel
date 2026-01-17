package com.solidvessel.inventory.adapter.out.product.db.mapper;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductJpaMapper {

    ProductJpaMapper INSTANCE = Mappers.getMapper(ProductJpaMapper.class);

    ProductJpaEntity toJpaEntity(Product product);

    Product toDomainModel(ProductJpaEntity product);
}
