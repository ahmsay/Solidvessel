package com.microshop.accountservice.entity;

public class Order {

    private Long id;
    private String status;
    private Long paymentId;

    public Order() { }

    public Order(final Long id, final String status, final Long paymentId) {
        this.id = id;
        this.status = status;
        this.paymentId = paymentId;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
