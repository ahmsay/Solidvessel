package com.solidvessel.shared.event;

public interface EventPublisher<T> {

    void publish(T event);
}
