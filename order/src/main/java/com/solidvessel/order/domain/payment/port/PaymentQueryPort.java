package com.solidvessel.order.domain.payment.port;

import com.solidvessel.order.domain.payment.datamodel.PaymentDataModel;

public interface PaymentQueryPort {

    PaymentDataModel getPaymentOfOrder(Long paymentId);
}
