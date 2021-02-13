package com.microshop.paymentservice.event;

public interface EventDispatcher {

    void send(PaymentSavedEvent event);
}
