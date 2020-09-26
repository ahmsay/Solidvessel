package com.shopping.orderservice.repositories;

import com.shopping.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();

    Order findById(long id);

    List<Order> findByCustomerId(long customerId);
}
