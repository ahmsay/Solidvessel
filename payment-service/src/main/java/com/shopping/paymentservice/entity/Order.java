package com.shopping.paymentservice.entity;

public class Order {

    private String id;
    private String status;
    private String customerId;

    public Order() {}

    public Order(final String id, final String status, final String customerId) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }
}
