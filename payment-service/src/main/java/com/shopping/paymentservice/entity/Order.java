package com.shopping.paymentservice.entity;

public class Order {

    private long id;
    private String status;
    private long customerId;

    public Order() {}

    public Order(final long id, final String status, final long customerId) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final long customerId) {
        this.customerId = customerId;
    }
}
