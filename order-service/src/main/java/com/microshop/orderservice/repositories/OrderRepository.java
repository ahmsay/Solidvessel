package com.microshop.orderservice.repositories;

import com.microshop.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Iterable<Order> findByCustomerId(Long customerId);
}
