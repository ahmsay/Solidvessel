package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.wrapper.ProductDTO;

import java.util.List;

public interface IProductService {

    Iterable<Product> findAll();

    ProductDTO findById(Long id, boolean pruned);

    Product findPrunedById(Long id);

    Iterable<Product> findByPaymentId(Long paymentId);

    Product save(Product product);

    void setPaymentIds(Long paymentId, List<Long> productIds);
}
