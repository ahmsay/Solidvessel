package com.solidvessel.order.domain.payment.port;

import com.solidvessel.order.domain.payment.datamodel.PaymentDataModel;

public interface PaymentPort {

    PaymentDataModel getPaymentOfOrder(Long paymentId);
}
