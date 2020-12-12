package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.dto.ProductDTO;
import com.microshop.inventoryservice.entity.Product;

import java.util.List;

public interface IProductService {

    Iterable<Product> findAll();

    ProductDTO findById(Long id);

    Iterable<Product> findByPaymentId(Long paymentId);

    Product save(Product product);

    void setPaymentIds(Long paymentId, List<Long> productIds);
}
