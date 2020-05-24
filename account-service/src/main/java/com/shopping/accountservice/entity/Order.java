package com.shopping.accountservice.entity;

public class Order {

    private String id;
    private String status;
    private String paymentId;

    public Order() {}

    public Order(final String id, final String status, final String paymentId) {
        this.id = id;
        this.status = status;
        this.paymentId = paymentId;
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final String paymentId) {
        this.paymentId = paymentId;
    }
}
