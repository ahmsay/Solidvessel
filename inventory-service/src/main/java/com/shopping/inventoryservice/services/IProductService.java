package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    List<Product> getProductsOfPayment(Long paymentId);
}
