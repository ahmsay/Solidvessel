package com.shopping.orderservice.entity;

import java.util.Set;

public class Customer {

    private String id;
    private String name;
    private Set<String> paymentIds;
    private Set<String> orderIds;

    public Customer(final String id, final String name, final Set<String> paymentIds, final Set<String> orderIds) {
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

    public Set<String> getPaymentIds() {
        return paymentIds;
    }

    public void setPaymentIds(final Set<String> paymentIds) {
        this.paymentIds = paymentIds;
    }

    public Set<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(final Set<String> orderIds) {
        this.orderIds = orderIds;
    }
}
