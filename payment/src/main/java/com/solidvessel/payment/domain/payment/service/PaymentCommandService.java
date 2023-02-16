package com.solidvessel.payment.domain.payment.service;

import com.solidvessel.payment.domain.payment.event.PaymentSavedEvent;
import com.solidvessel.payment.domain.payment.model.Payment;
import com.solidvessel.payment.domain.payment.port.PaymentPort;
import com.solidvessel.payment.domain.payment.port.PaymentSavedPort;
import com.solidvessel.payment.domain.payment.service.command.AddPaymentCommand;
import org.springframework.stereotype.Service;

@Service
public class PaymentCommandService {

    private final PaymentPort paymentPort;
    private final PaymentSavedPort paymentSavedPort;

    public PaymentCommandService(PaymentPort paymentPort, PaymentSavedPort paymentSavedPort) {
        this.paymentPort = paymentPort;
        this.paymentSavedPort = paymentSavedPort;
    }

    public void add(AddPaymentCommand command) {
        Long paymentId = paymentPort.add(new Payment(command.totalCharge(), command.customerId()));
        paymentSavedPort.sendPaymentSavedEvent(new PaymentSavedEvent(paymentId, command.customerId(), command.productIds()));
    }
}
