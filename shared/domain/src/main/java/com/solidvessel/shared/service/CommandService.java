package com.solidvessel.shared.service;

public interface CommandService<T> {

    OperationResult execute(T executable);
}
