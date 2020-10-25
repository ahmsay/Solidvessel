package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.dto.ProductDTO;
import com.microshop.inventoryservice.entity.Product;

public interface IProductService {

    Iterable<Product> findAll();

    Product findById(Long id);

    Iterable<Product> findByPaymentId(Long paymentId);

    Product save(Product product);

    ProductDTO setPaymentIds(ProductDTO productDTO);
}
