package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Product;

import java.util.List;

public interface IProductService {

    Iterable<Product> findAll();

    Product findById(Long id);

    Iterable<Product> findByPaymentId(Long paymentId);

    Product save(Product product);

    String setPaymentIds(Long paymentId, List<Long> productIds);
}
