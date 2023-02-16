package com.solidvessel.payment.domain.payment.port;

import com.solidvessel.payment.domain.payment.event.PaymentSavedEvent;

public interface PaymentSavedPort {

    void sendPaymentSavedEvent(PaymentSavedEvent event);
}
