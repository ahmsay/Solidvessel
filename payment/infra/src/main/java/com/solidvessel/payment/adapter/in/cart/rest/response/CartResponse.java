package com.solidvessel.payment.adapter.in.cart.rest.response;

import com.solidvessel.payment.adapter.out.product.rest.response.ProductResponse;
import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.product.model.Product;

import java.util.List;
import java.util.Map;

public record CartResponse(Long id, String customerId, List<ProductResponse> products) {

    public static CartResponse from(Cart cart, List<Product> products) {
        Map<Long, Integer> productQuantities = cart.getProductQuantities();
        List<ProductResponse> productsWithQuantitiesInCart = products.stream()
                .map(product -> new ProductResponse(product.getId(), productQuantities.get(product.getId()), product.getName(), product.getPrice()))
                .toList();
        return new CartResponse(cart.getId(), cart.getCustomerId(), productsWithQuantitiesInCart);
    }
}
