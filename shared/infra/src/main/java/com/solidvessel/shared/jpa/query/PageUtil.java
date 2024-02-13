package com.solidvessel.shared.jpa.query;

import com.solidvessel.shared.query.QueryOptions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtil {

    public static Pageable withPage(QueryOptions queryOptions) {
        return PageRequest.of(queryOptions.pageNumber(), queryOptions.pageSize());
    }
}
