package com.shopping.inventoryservice.repositories;

import com.shopping.inventoryservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByPaymentId(Long paymentId);
}
