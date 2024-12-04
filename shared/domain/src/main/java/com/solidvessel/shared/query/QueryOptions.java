package com.solidvessel.shared.query;

public record QueryOptions(Integer pageNumber, Integer pageSize) {

    public QueryOptions(Integer pageNumber) {
        this(pageNumber, 10);
    }

    public static QueryOptions of(Integer pageNumber, Integer pageSize) {
        if (pageSize == null) {
            return new QueryOptions(pageNumber);
        }
        return new QueryOptions(pageNumber, pageSize);
    }
}
