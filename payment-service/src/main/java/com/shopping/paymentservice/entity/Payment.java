package com.shopping.paymentservice.entity;

public class Payment {

    private String id;
    private double totalCharge;
    private String customerId;

    public Payment(final String id, final double totalCharge, final String customerId) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.customerId = customerId;
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
}
