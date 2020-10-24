package com.shopping.inventoryservice.services;

import com.shopping.inventoryservice.dto.ProductDTO;
import com.shopping.inventoryservice.entity.Product;

public interface IProductService {

    Iterable<Product> getAllProducts();

    Product getProductById(Long id);

    Iterable<Product> getProductsOfPayment(Long paymentId);

    Product addProduct(Product product);

    ProductDTO setPayments(ProductDTO productDTO);
}
