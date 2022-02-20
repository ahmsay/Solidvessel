package com.microshop.payment.response;

public record PaymentDetailResponse(Long id, Double totalCharge, CustomerResponse customer,
                                    ProductsResponse productsResponse) {
}
