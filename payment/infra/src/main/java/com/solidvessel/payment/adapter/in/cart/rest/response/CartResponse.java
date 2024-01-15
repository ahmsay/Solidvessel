package com.solidvessel.payment.adapter.in.cart.rest.response;

import com.solidvessel.payment.adapter.out.product.rest.response.ProductResponse;
import com.solidvessel.payment.cart.model.Cart;

import java.util.List;

public record CartResponse(Long id, String customerId, List<ProductResponse> products) {

    public static CartResponse from(Cart cart) {
        var products = cart.getProducts().values().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getQuantity()
                )).toList();
        return new CartResponse(cart.getId(), cart.getCustomerId(), products);
    }
}
