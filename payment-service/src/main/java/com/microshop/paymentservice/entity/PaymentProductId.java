package com.microshop.paymentservice.entity;

import java.io.Serializable;

public class PaymentProductId implements Serializable {

    private Long paymentId;
    private Long productId;

    public PaymentProductId() { }

    public PaymentProductId(final Long paymentId, final Long productId) {
        this.paymentId = paymentId;
        this.productId = productId;
    }
}
