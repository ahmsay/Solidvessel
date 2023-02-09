package com.solidvessel.account.domain.order.port;

import com.solidvessel.account.infra.adapter.order.rest.response.OrdersResponse;

public interface OrderPort {

    OrdersResponse getOrdersOfCustomer(Long customerId, String session);
}
