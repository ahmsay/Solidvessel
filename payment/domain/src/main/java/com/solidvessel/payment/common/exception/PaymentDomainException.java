package com.solidvessel.payment.common.exception;

public class PaymentDomainException extends RuntimeException {

    public PaymentDomainException(String errorMessage) {
        super(errorMessage);
    }
}
