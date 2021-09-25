package com.microshop.paymentservice.dto;

import com.microshop.paymentservice.entity.Payment;

import java.util.List;

public class SavePaymentDTO {

    private Payment payment;
    private List<Long> productIds;

    public SavePaymentDTO() { }

    public SavePaymentDTO(final Payment payment, final List<Long> productIds) {
        this.payment = payment;
        this.productIds = productIds;
    }

    public Payment getPayment() {
        return payment;
    }

    public List<Long> getProductIds() {
        return productIds;
    }
}
