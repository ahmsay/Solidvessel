package com.solidvessel.account.domain.common.exception;

public class AccountDomainException extends RuntimeException {

    public AccountDomainException(String errorMessage) {
        super(errorMessage);
    }
}
