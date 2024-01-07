package com.solidvessel.account.adapter.in.customer.rest.response;

import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.account.customer.model.Customer;
import com.solidvessel.account.order.model.Order;
import com.solidvessel.account.payment.model.Payment;

import java.time.LocalDate;
import java.util.List;

public record CustomerDetailResponse(String id, String firstName, String lastName, LocalDate birthDate,
                                     String email, String phoneNumber, List<OrderResponse> orders,
                                     List<PaymentResponse> payments) {

    public static CustomerDetailResponse from(Customer customer, List<Order> orders,
                                              List<Payment> payments) {
        return new CustomerDetailResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                orders.stream().map(OrderResponse::from).toList(),
                payments.stream().map(PaymentResponse::from).toList()
        );
    }
}
