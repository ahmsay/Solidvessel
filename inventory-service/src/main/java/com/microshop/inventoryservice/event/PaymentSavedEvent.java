package com.microshop.inventoryservice.event;

import java.io.Serializable;
import java.util.List;

public class PaymentSavedEvent implements Serializable {

    private Long paymentId;
    private List<Long> productIds;

    public PaymentSavedEvent() { }

    public PaymentSavedEvent(final Long paymentId, final List<Long> productIds) {
        this.paymentId = paymentId;
        this.productIds = productIds;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }
}
