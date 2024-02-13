package com.solidvessel.shared.query;

public record QueryOptions(int pageNumber, int pageSize) {

    public QueryOptions(int pageNumber) {
        this(pageNumber, 10);
    }

    public static QueryOptions of(Integer pageNumber, Integer pageSize) {
        if (pageSize == null) {
            return new QueryOptions(pageNumber);
        }
        return new QueryOptions(pageNumber, pageSize);
    }
}
