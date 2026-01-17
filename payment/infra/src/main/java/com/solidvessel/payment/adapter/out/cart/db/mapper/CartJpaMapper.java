package com.solidvessel.payment.adapter.out.cart.db.mapper;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CartJpaMapper {

    CartJpaMapper INSTANCE = Mappers.getMapper(CartJpaMapper.class);

    @Mapping(source = "products", target = "products", qualifiedByName = "productsMapToList")
    CartJpaEntity toJpaEntity(Cart cart);

    @Mapping(source = "products", target = "products", qualifiedByName = "productsListToMap")
    Cart toDomainModel(CartJpaEntity cart);

    static List<ProductEmbeddable> productsMapToList(Map<Long, Product> products) {
        if (products == null) return null;
        return products.values().stream()
                .map(ProductEmbeddable::from)
                .toList();
    }

    static Map<Long, Product> productsListToMap(List<ProductEmbeddable> products) {
        if (products == null) return null;
        return products.stream()
                .collect(Collectors.toMap(
                        ProductEmbeddable::getProductId,
                        ProductEmbeddable::toDomainModel
                ));
    }
}
