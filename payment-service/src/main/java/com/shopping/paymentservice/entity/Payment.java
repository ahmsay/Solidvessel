package com.shopping.paymentservice.entity;

import java.util.Set;

public class Payment {

    private int id;
    private double totalCharge;
    private int customerId;
    private Set<Integer> productIds;
    private int orderId;

    public Payment(final int id, final double amount, final int customerId, final Set<Integer> productIds, final int orderId) {
        this.id = id;
        this.totalCharge = amount;
        this.customerId = customerId;
        this.productIds = productIds;
        this.orderId = orderId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final int customerId) {
        this.customerId = customerId;
    }

    public Set<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(final Set<Integer> productIds) {
        this.productIds = productIds;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }
}
