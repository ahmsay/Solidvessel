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
        orders.add(new Order("1", "Delivered", "1", "1"));
        orders.add(new Order("2", "On the way", "2", "2"));
        orders.add(new Order("3", "Crashed", "2", "3"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrderById(final String id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Order> getOrdersOfCustomer(final String customerId) {
        return orders.stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
}
