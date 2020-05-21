package com.shopping.accountservice.entity;

import java.util.Set;

public class Payment {

    private int id;
    private double totalCharge;
    private Set<Integer> productIds;

    public Payment(final int id, final double amount, final Set<Integer> productIds) {
        this.id = id;
        this.totalCharge = amount;
        this.productIds = productIds;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(final double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Set<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(final Set<Integer> productIds) {
        this.productIds = productIds;
    }
}
