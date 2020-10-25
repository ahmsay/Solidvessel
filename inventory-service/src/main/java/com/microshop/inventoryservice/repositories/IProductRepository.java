package com.microshop.inventoryservice.repositories;

import com.microshop.inventoryservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByPaymentId(Long paymentId);
}
