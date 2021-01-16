package com.microshop.paymentservice.event;

public interface IEventDispatcher {

    void send(PaymentSavedEvent event);
}
