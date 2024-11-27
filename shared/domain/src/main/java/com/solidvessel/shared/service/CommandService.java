package com.solidvessel.shared.service;

public interface CommandService<T, K> {

    K execute(T command);
}
