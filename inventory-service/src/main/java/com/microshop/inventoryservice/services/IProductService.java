package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.dto.ProductDTO;
import com.microshop.inventoryservice.entity.Product;

import java.util.List;

public interface IProductService {

    Iterable<Product> findAll();

    ProductDTO findById(Long id);

    Product findPrunedById(Long id);

    Product save(Product product);
}
