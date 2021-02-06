package com.microshop.paymentservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PaymentProductId.class)
public class PaymentProduct {

    @Id
    private Long paymentId;

    @Id
    private Long productId;

    public PaymentProduct(Long paymentId, Long productId) {
        this.paymentId = paymentId;
        this.productId = productId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getProductId() {
        return productId;
    }
}
