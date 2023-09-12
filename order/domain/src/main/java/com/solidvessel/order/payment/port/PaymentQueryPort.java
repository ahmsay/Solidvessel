package com.solidvessel.order.payment.port;

import com.solidvessel.order.payment.datamodel.PaymentDataModel;

public interface PaymentQueryPort {

    PaymentDataModel getPaymentOfOrder(Long paymentId);
}
