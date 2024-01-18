package com.solidvessel.payment.product.event;

import com.solidvessel.payment.payment.service.UpdatePaymentStatusCommand;

public record ProductsCheckedEvent(Long paymentId, boolean areProductsAvailable, String customerId) {

    public UpdatePaymentStatusCommand toCommand() {
        return new UpdatePaymentStatusCommand(paymentId, areProductsAvailable, customerId);
    }
}
