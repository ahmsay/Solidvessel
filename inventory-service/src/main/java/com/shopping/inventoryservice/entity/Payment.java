package com.shopping.inventoryservice.entity;

public class Payment {

    private String id;
    private double totalCharge;
    private String customerId;
    private String orderId;

    public Payment() {}

    public Payment(final String id, final double totalCharge, final String customerId, final String orderId) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.customerId = customerId;
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(final double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }
}
