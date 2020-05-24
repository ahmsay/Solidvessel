package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.entity.Product;

import java.util.List;
import java.util.Set;

public interface IProductService {

    Set<Product> getAllProducts();

    Product getProductById(String id);

    List<Product> getProductsByIds(List<String> productIds);
}
