package com.microshop.inventoryservice.repositories;

import com.microshop.inventoryservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
