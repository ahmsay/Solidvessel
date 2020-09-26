package com.shopping.accountservice.entity;

public class Payment {

    private long id;
    private double totalCharge;

    public Payment() {}

    public Payment(final long id, final double amount) {
        this.id = id;
        this.totalCharge = amount;
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
}
