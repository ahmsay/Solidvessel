package com.solidvessel.payment.adapter.in.cart.rest.datamodel;

import com.solidvessel.payment.adapter.out.product.rest.datamodel.ProductDataModel;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;

import java.util.List;
import java.util.Map;

public record CartDataModel(Long id, String customerId, List<ProductDataModel> products) {

    public static CartDataModel from(Cart cart, List<Product> products) {
        Map<Long, Integer> productQuantities = cart.getProductQuantities();
        List<ProductDataModel> productsWithQuantitiesInCart = products.stream()
                .map(product -> new ProductDataModel(product.getId(), productQuantities.get(product.getId()), product.getName(), product.getPrice()))
                .toList();
        return new CartDataModel(cart.getId(), cart.getCustomerId(), productsWithQuantitiesInCart);
    }
}
