package com.solidvessel.payment.adapter.in.cart.rest.mapper;

import com.solidvessel.payment.adapter.in.cart.rest.response.CartResponse;
import com.solidvessel.payment.adapter.in.product.rest.mapper.ProductWebMapper;
import com.solidvessel.payment.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CartWebMapper {

    @Autowired
    protected ProductWebMapper productWebMapper;

    @Mapping(source = "products", target = "products", qualifiedByName = "toProductResponseList")
    public abstract CartResponse toResponse(Cart cart);

    @Named("toProductResponseList")
    protected List<ProductResponse> toProductResponseList(Map<Long, Product> products) {
        if (products == null) {
            return new ArrayList<>();
        }
        return products.values().stream()
                .map(productWebMapper::toResponse)
                .collect(Collectors.toList());
    }
}
