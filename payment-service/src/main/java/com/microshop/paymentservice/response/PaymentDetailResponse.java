package com.microshop.paymentservice.response;

import java.util.List;

public record PaymentDetailResponse(Long id, Double totalCharge, CustomerResponse customer,
                                    List<ProductResponse> products) {
}
