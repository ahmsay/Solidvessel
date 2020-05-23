package com.shopping.orderservice.entity;

public class Order {

    private String id;
    private String status;
    private String customerId;
    private String paymentId;

    public Order(final String id, final String status, final String customerId, final String paymentId) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final String paymentId) {
        this.paymentId = paymentId;
    }
}
