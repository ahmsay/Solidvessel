package com.solidvessel.inventory.product.port;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.shared.query.QueryOptions;

import java.util.List;

public interface ProductQueryPort {

    List<Product> getProducts(QueryOptions queryOptions);

    Product getById(Long id);

    List<Product> getByIds(List<Long> ids);
}
