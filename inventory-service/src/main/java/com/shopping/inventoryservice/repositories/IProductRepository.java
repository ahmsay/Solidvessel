package com.shopping.inventoryservice.repositories;

import com.shopping.inventoryservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    Product findById(long id);

    List<Product> findByPaymentId(long paymentId);
}
