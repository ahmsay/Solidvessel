package com.solidvessel.account.infra.adapter.customer.rest.response;

import com.solidvessel.account.infra.adapter.order.rest.response.OrdersResponse;
import com.solidvessel.account.infra.adapter.payment.rest.response.PaymentsResponse;

public record CustomerDetailResponse(Long id, String firstName, String lastName, OrdersResponse ordersResponse,
                                     PaymentsResponse paymentsResponse) {

    public static CustomerDetailResponse from(CustomerResponse customer, OrdersResponse orders, PaymentsResponse payments) {
        return new CustomerDetailResponse(customer.id(), customer.firstName(), customer.lastName(), orders, payments);
    }
}
