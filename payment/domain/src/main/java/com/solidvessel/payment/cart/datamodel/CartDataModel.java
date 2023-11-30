package com.solidvessel.payment.cart.datamodel;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.datamodel.ProductDataModel;

import java.util.List;
import java.util.Map;

public record CartDataModel(Long id, String customerId, List<ProductDataModel> products) {

    public static CartDataModel from(Cart cart, List<ProductDataModel> products) {
        Map<Long, Integer> productQuantities = cart.getProductQuantities();
        List<ProductDataModel> productsWithQuantitiesInCart = products.stream()
                .map(product -> new ProductDataModel(product.id(), productQuantities.get(product.id()), product.name(), product.price()))
                .toList();
        return new CartDataModel(cart.getId(), cart.getCustomerId(), productsWithQuantitiesInCart);
    }
}
