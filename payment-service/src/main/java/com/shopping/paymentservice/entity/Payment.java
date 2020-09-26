package com.shopping.paymentservice.entity;

public class Payment {

    private long id;
    private double totalCharge;
    private long customerId;
    private long orderId;

    public Payment(final long id, final double totalCharge, final long customerId, final long orderId) {
        this.id = id;
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
