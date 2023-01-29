package com.solidvessel.account.response;

import java.util.List;

public record OrdersResponse(List<OrderResponse> orders, String error) {

    public static OrdersResponse from(List<OrderResponse> orders) {
        return new OrdersResponse(orders, "");
    }
}
