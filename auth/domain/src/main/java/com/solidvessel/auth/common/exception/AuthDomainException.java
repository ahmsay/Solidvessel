package com.solidvessel.auth.common.exception;

public class AuthDomainException extends RuntimeException {

    public AuthDomainException(String errorMessage) {
        super(errorMessage);
    }
}
