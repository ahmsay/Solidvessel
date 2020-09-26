package com.shopping.orderservice.repositories;

import com.shopping.orderservice.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository implements IOrderRepository {

    private List<Order> orders;

    public OrderRepository() {
        orders = new ArrayList<>();
        orders.add(new Order(1L, "Delivered", 1L, 1L));
        orders.add(new Order(2L, "On the way", 2L, 2L));
        orders.add(new Order(3L, "Crashed", 2L, 3L));
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrderById(final long id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Order> getOrdersOfCustomer(final long customerId) {
        return orders.stream()
                .filter(order -> order.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }
}
