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
        orders.add(new Order(1, "Delivered"));
        orders.add(new Order(2, "On the way"));
        orders.add(new Order(3, "Crashed"));
        orders.add(new Order(4, "Delivered"));
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
