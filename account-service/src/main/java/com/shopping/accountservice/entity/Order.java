package com.shopping.accountservice.entity;

public class Order {

    private long id;
    private String status;
    private long paymentId;

    public Order() {}

    public Order(final long id, final String status, final long paymentId) {
        this.id = id;
        this.status = status;
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

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final long paymentId) {
        this.paymentId = paymentId;
    }
}
