package com.solidvessel.order.domain.order.model;

public class Order {

    private Long id;
    private String status;
    private Long customerId;
    private Long paymentId;

    public Order(Long id, String status, Long customerId, Long paymentId) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
        this.paymentId = paymentId;
    }

    public Order(String status, Long customerId, Long paymentId) {
        this.status = status;
        this.customerId = customerId;
        this.paymentId = paymentId;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
