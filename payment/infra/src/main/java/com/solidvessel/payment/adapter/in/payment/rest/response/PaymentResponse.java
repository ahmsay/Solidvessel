package com.solidvessel.payment.adapter.in.payment.rest.response;

import com.solidvessel.payment.adapter.in.product.rest.response.ProductResponse;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;

import java.util.List;

public record PaymentResponse(Long id, String customerId, List<ProductResponse> products, Double totalCharge,
                              PaymentStatus status) {

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getCustomerId(),
                payment.getProducts().stream().map(ProductResponse::from).toList(),
                payment.getTotalPrice(),
                payment.getStatus()
        );
    }
}
