package com.solidvessel.order.common.exception;

public class OrderDomainException extends RuntimeException {

    public OrderDomainException(String errorMessage) {
        super(errorMessage);
    }
}
