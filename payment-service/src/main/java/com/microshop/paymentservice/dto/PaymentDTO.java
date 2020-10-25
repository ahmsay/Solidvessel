package com.microshop.paymentservice.dto;

import java.util.List;

public class PaymentDTO {

    private Double totalCharge;
    private Long customerId;
    private List<Long> productIds;

    public PaymentDTO() { }

    public PaymentDTO(final Double totalCharge, final Long customerId, final List<Long> productIds) {
        this.totalCharge = totalCharge;
        this.customerId = customerId;
        this.productIds = productIds;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }
}
