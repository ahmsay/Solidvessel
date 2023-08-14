package com.solidvessel.auth.domain.common.exception;

public class AuthDomainException extends RuntimeException {

    public AuthDomainException(String errorMessage) {
        super(errorMessage);
    }
}
