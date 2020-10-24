package com.shopping.orderservice.repositories;

import com.shopping.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface IOrderRepository extends CrudRepository<Order, Long> {

    Iterable<Order> findByCustomerId(Long customerId);
}
