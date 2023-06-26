package com.solidvessel.payment.domain.cart.datamodel;

import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;

import java.util.List;
import java.util.Map;

public record CartDataModel(Long id, Long customerId, List<ProductDataModel> products) {

    public static CartDataModel from(Cart cart, List<ProductDataModel> products) {
        Map<Long, Integer> productQuantities = cart.getProducts();
        List<ProductDataModel> productsWithQuantitiesInCart = products.stream()
                .map(product -> new ProductDataModel(product.id(), productQuantities.get(product.id()), product.name(), product.price()))
                .toList();
        return new CartDataModel(cart.getId(), cart.getCustomerId(), productsWithQuantitiesInCart);
    }
}
