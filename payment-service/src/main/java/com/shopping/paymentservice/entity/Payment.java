package com.shopping.paymentservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Double totalCharge;

    @NotNull
    private Long customerId;

    @NotNull
    private Long orderId;

    Payment() { }

    public Payment(final Double totalCharge, final Long customerId, final Long orderId) {
        this.totalCharge = totalCharge;
        this.customerId = customerId;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(final Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }
}
