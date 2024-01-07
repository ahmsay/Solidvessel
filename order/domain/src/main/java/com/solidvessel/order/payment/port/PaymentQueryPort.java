package com.solidvessel.order.payment.port;

import com.solidvessel.order.payment.model.Payment;

public interface PaymentQueryPort {

    Payment getPaymentOfOrder(Long paymentId);
}
