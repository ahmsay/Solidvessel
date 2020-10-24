package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.dto.ProductDTO;
import com.shopping.inventoryservice.entity.Product;

public interface IProductService {

    Iterable<Product> findAll();

    Product findById(Long id);

    Iterable<Product> findByPaymentId(Long paymentId);

    Product save(Product product);

    ProductDTO setPaymentIds(ProductDTO productDTO);
}
