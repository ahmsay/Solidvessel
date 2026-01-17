package com.solidvessel.payment.adapter.in.cart.rest.response;

import com.solidvessel.payment.adapter.in.cart.rest.mapper.CartWebMapper;
import com.solidvessel.payment.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.payment.cart.model.Cart;

import java.util.List;

public record CartResponse(Long id, String customerId, List<ProductResponse> products, Double totalPrice) {

    public static CartResponse from(Cart cart) {
        return CartWebMapper.INSTANCE.toResponse(cart);
    }
}
