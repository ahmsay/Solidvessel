package com.microshop.orderservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String status;

    @NotNull
    private Long customerId;

    @NotNull
    private Long paymentId;

    Order() { }

    public Order(final String status, final Long customerId, final Long paymentId) {
        this.status = status;
        this.customerId = customerId;
        this.paymentId = paymentId;
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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final Long paymentId) {
        this.paymentId = paymentId;
    }
}
