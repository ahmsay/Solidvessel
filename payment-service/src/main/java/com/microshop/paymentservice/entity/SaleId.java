package com.microshop.paymentservice.entity;

import java.io.Serializable;

public class SaleId implements Serializable {

    private Long paymentId;
    private Long productId;

    public SaleId() { }

    public SaleId(final Long paymentId, final Long productId) {
        this.paymentId = paymentId;
        this.productId = productId;
    }
}
