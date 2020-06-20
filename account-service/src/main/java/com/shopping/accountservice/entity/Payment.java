package com.shopping.accountservice.entity;

import java.util.List;

public class Payment {

    private String id;
    private double totalCharge;
    private List<String> productIds;
    private String orderId;

    public Payment() {}

    public Payment(final String id, final double amount, final List<String> productIds, final String orderId) {
        this.id = id;
        this.totalCharge = amount;
        this.productIds = productIds;
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

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(final List<String> productIds) {
        this.productIds = productIds;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }
}
