package com.solidvessel.payment.adapter.out.cart.db.mapper;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.product.db.entity.ProductEmbeddable;
import com.solidvessel.payment.adapter.out.product.db.mapper.ProductJpaMapper;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CartJpaMapper {

    @Autowired
    protected ProductJpaMapper productJpaMapper;

    @Mapping(source = "products", target = "products", qualifiedByName = "toProductEmbeddableList")
    public abstract CartJpaEntity toJpaEntity(Cart cart);

    @Mapping(source = "products", target = "products", qualifiedByName = "toProductMap")
    public abstract Cart toDomainModel(CartJpaEntity cart);

    @Named("toProductEmbeddableList")
    protected List<ProductEmbeddable> toProductEmbeddableList(Map<Long, Product> products) {
        if (products == null) {
            return new ArrayList<>();
        }
        return products.values().stream()
                .map(productJpaMapper::toEmbeddable)
                .collect(Collectors.toList());
    }

    @Named("toProductMap")
    protected Map<Long, Product> toProductMap(List<ProductEmbeddable> products) {
        if (products == null) {
            return new HashMap<>();
        }
        return products.stream()
                .map(productJpaMapper::toDomainModel)
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
