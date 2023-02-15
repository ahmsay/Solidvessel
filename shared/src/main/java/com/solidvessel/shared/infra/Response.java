package com.solidvessel.shared.infra;

public record Response<T>(T data, Boolean error) {

    public Response(T data) {
        this(data, false);
    }
}
