package com.solidvessel.shared.service;

public interface VoidCommandService<T> {

    void execute(T command);
}
