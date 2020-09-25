package com.shopping.accountservice.entity;

public class Payment {

    private String id;
    private double totalCharge;

    public Payment() {}

    public Payment(final String id, final double amount, final String orderId) {
        this.id = id;
        this.totalCharge = amount;
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
}
