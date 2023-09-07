package com.solidvessel.inventory.domain.product.port;

import com.solidvessel.inventory.domain.product.model.Product;

import java.util.List;

public interface ProductPort {

    void save(Product product);

    void saveProducts(List<Product> products);
}
