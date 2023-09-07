package com.solidvessel.payment.domain.payment.port;

import com.solidvessel.payment.domain.payment.model.Payment;

public interface PaymentPort {

    Long save(Payment payment);
}
