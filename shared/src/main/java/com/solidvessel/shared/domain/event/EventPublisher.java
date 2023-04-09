package com.solidvessel.shared.domain.event;

public interface EventPublisher<T> {

    void publish(T event);
}
