package com.solidvessel.shared.domain.service;

public interface CommandService<T> {

    OperationResult execute(T command);
}
