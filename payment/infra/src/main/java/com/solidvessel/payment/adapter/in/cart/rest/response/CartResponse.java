package com.solidvessel.payment.adapter.in.cart.rest.response;

import com.solidvessel.payment.adapter.in.product.rest.response.ProductResponse;

import java.util.List;

public record CartResponse(Long id, String customerId, List<ProductResponse> products, Double totalPrice) {
}
