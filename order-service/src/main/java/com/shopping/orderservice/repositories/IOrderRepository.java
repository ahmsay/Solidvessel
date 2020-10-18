package com.shopping.orderservice.repositories;

import com.shopping.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();

    Optional<Order> findById(Long id);

    List<Order> findByCustomerId(Long customerId);
}
