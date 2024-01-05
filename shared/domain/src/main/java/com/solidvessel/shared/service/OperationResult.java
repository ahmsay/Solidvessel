package com.solidvessel.shared.service;

public record OperationResult(String message, ResultType resultType) {

    public static OperationResult defaultSuccessResult() {
        return new OperationResult("Operation is successful.", ResultType.SUCCESS);
    }
}
