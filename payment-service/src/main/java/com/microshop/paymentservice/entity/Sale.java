package com.microshop.paymentservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(SaleId.class)
public class Sale {

    @Id
    private Long paymentId;

    @Id
    private Long productId;

    Sale() { }

    public Sale(Long paymentId, Long productId) {
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
