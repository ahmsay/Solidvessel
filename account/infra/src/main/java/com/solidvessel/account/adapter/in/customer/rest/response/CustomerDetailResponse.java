package com.solidvessel.account.adapter.in.customer.rest.response;

import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;

import java.time.LocalDate;
import java.util.List;

public record CustomerDetailResponse(String id, String firstName, String lastName, LocalDate birthDate,
                                     String email, String phoneNumber, List<OrderResponse> orders,
                                     List<PaymentResponse> payments) {

    public static CustomerDetailResponse from(CustomerResponse customer, List<OrderResponse> orders,
                                              List<PaymentResponse> payments) {
        return new CustomerDetailResponse(
                customer.id(),
                customer.firstName(),
                customer.lastName(),
                customer.birthDate(),
                customer.email(),
                customer.phoneNumber(),
                orders,
                payments
        );
    }
}
