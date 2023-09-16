package com.solidvessel.account.common.exception;

public class AccountDomainException extends RuntimeException {

    public AccountDomainException(String errorMessage) {
        super(errorMessage);
    }
}
