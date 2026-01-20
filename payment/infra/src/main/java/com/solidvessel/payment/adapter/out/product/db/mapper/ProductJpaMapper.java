package com.solidvessel.payment.adapter.out.product.db.mapper;

import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductJpaMapper {

    @Mapping(source = "id", target = "productId")
    ProductEmbeddable toEmbeddable(Product product);

    @Mapping(source = "productId", target = "id")
    Product toDomainModel(ProductEmbeddable product);
}
