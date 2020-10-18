package com.shopping.paymentservice.entity;

public class Order {

    private Long id;
    private String status;
    private Long customerId;

    public Order() {}

    public Order(final Long id, final String status, final Long customerId) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }
}
