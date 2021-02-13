package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Product;

import java.util.List;

public interface ProductService {

    Iterable<Product> findAll();

    Product findById(Long id);

    Iterable<Product> findByIds(List<Long> ids);

    Product save(Product product);
}
