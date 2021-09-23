package com.microshop.inventoryservice.repositories;

import com.microshop.inventoryservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByIdIn(List<Long> ids);
}
