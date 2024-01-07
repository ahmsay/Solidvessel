package com.solidvessel.inventory.product.port;

import com.solidvessel.inventory.product.model.Product;

import java.util.List;

public interface ProductQueryPort {

    List<Product> getAll();

    Product getById(Long id);

    List<Product> getByIds(List<Long> ids);

    boolean isAvailable(Long id, int quantity);
}
