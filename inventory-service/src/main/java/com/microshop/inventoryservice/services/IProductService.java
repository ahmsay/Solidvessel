package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Product;

public interface IProductService {

    Iterable<Product> findAll();

    Product findById(Long id);

    Product save(Product product);
}
