package com.solidvessel.inventory.common.exception;

public class InventoryDomainException extends RuntimeException {

    public InventoryDomainException(String errorMessage) {
        super(errorMessage);
    }
}
