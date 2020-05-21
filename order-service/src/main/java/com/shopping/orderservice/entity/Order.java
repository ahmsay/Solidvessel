package com.shopping.orderservice.entity;

public class Order {

    private int id;
    private String status;
    private int customerId;
    private int paymentId;

    public Order(final int id, final String status, final int customerId, final int paymentId) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
        this.paymentId = paymentId;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final int customerId) {
        this.customerId = customerId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final int paymentId) {
        this.paymentId = paymentId;
    }
}
