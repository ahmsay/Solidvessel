package com.shopping.inventoryservice.repositories;

import com.shopping.inventoryservice.entity.Product;

import java.util.Set;

public interface IProductRepository {

    Set<Product> getAllProducts();

    Product getProductById(String id);
}
