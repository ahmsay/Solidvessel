package com.solidvessel.inventory.repository;

import com.solidvessel.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPaymentId(final Long paymentId);
}