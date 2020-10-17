package com.shopping.paymentservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private double totalCharge;

    @NotNull
    private long customerId;

    @NotNull
    private long orderId;

    Payment() { }

    public Payment(final double totalCharge, final long customerId, final long orderId) {
        this.totalCharge = totalCharge;
        this.customerId = customerId;
        this.orderId = orderId;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(final double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final long customerId) {
        this.customerId = customerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }
}
