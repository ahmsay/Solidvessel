package com.shopping.orderservice.entity;

public class Order {

    private long id;
    private String status;
    private long customerId;
    private long paymentId;

    public Order(final long id, final String status, final long customerId, final long paymentId) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
        this.paymentId = paymentId;
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

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final long paymentId) {
        this.paymentId = paymentId;
    }
}
