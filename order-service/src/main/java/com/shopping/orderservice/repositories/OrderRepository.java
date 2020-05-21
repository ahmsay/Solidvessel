package com.shopping.orderservice.repositories;

import com.shopping.orderservice.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class OrderRepository implements IOrderRepository {

    private Set<Order> orders;

    public OrderRepository() {
        orders = new HashSet<>();
        orders.add(new Order(1, "Delivered", 1, 1));
        orders.add(new Order(2, "On the way", 2, 2));
        orders.add(new Order(3, "Crashed", 2, 3));
    }

    @Override
    public Set<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrderById(final int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findAny()
                .orElse(null);
    }
}
