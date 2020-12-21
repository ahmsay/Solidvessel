package com.microshop.accountservice.wrapper;

import java.util.List;

public class CustomerDTO {

    private final Long id;
    private final String name;
    private List<Payment> paymentList;
    private List<Order> orderList;

    public CustomerDTO(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerDTO(final Long id, final String name, final List<Payment> paymentList, final List<Order> orderList) {
        this.id = id;
        this.name = name;
        this.paymentList = paymentList;
        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
