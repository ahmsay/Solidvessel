package com.shopping.inventoryservice.repositories;

import com.shopping.inventoryservice.entity.Product;

import java.util.List;

public interface IProductRepository {

    List<Product> getAllProducts();

    Product getProductById(String id);

    List<Product> getProductsOfPayment(String paymentId);
}
