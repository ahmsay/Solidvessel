package com.solidvessel.payment.payment.port;

import com.solidvessel.payment.payment.model.Payment;

public interface PaymentPort {

    Long create(Payment payment);

    void update(Payment payment);
}
