package com.solidvessel.payment.domain.common.exception;

public class PaymentDomainException extends RuntimeException {

    public PaymentDomainException(String errorMessage) {
        super(errorMessage);
    }
}
