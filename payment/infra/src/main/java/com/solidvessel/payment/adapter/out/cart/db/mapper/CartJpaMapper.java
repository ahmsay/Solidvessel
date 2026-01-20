package com.solidvessel.payment.adapter.out.cart.db.mapper;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.adapter.out.product.db.mapper.ProductJpaMapper;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {ProductJpaMapper.class})
public interface CartJpaMapper {

    @Mapping(source = "products", target = "products")
    CartJpaEntity toJpaEntity(Cart cart);

    @Mapping(source = "products", target = "products")
    Cart toDomainModel(CartJpaEntity cart);

    default List<ProductEmbeddable> productsMapToList(Map<Long, Product> products, @Context ProductJpaMapper productJpaMapper) {
        if (products == null) return null;
        return products.values().stream()
                .map(productJpaMapper::toEmbeddable)
                .toList();
    }

    default Map<Long, Product> productsListToMap(List<ProductEmbeddable> products, @Context ProductJpaMapper productJpaMapper) {
        if (products == null) return null;
        return products.stream()
                .collect(Collectors.toMap(
                        ProductEmbeddable::getProductId,
                        productJpaMapper::toDomainModel
                ));
    }
}
