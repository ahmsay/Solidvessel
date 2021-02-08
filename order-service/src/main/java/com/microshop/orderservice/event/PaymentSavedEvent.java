package com.microshop.orderservice.event;

import java.io.Serializable;

public class PaymentSavedEvent implements Serializable {

    private Long paymentId;
    private Long customerId;

    public PaymentSavedEvent() { }

    public PaymentSavedEvent(final Long paymentId, final Long customerId) {
        this.paymentId = paymentId;
        this.customerId = customerId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
