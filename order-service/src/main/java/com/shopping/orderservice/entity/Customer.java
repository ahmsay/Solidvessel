package com.shopping.orderservice.entity;

import java.util.List;

public class Customer {

    private String id;
    private String name;
    private List<String> paymentIds;
    private List<String> orderIds;

    public Customer() {}

    public Customer(final String id, final String name, final List<String> paymentIds, final List<String> orderIds) {
        this.id = id;
        this.name = name;
        this.paymentIds = paymentIds;
        this.orderIds = orderIds;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getPaymentIds() {
        return paymentIds;
    }

    public void setPaymentIds(final List<String> paymentIds) {
        this.paymentIds = paymentIds;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(final List<String> orderIds) {
        this.orderIds = orderIds;
    }
}
