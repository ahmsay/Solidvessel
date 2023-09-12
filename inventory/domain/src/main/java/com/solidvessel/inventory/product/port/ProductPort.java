package com.solidvessel.inventory.product.port;

import com.solidvessel.inventory.product.model.Product;

import java.util.List;

public interface ProductPort {

    void save(Product product);

    void saveProducts(List<Product> products);
}
