package com.microshop.payment.response;

import java.util.List;

public record ProductsResponse(List<ProductResponse> products, String error) {

    public static ProductsResponse from(List<ProductResponse> products) {
        return new ProductsResponse(products, "");
    }
}
