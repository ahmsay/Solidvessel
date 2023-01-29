package com.solidvessel.payment.response;

public record PaymentDetailResponse(Long id, Double totalCharge, CustomerResponse customer,
                                    ProductsResponse productsResponse) {
}
