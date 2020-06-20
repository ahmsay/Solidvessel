package com.shopping.orderservice.entity;

import java.util.List;

public class Payment {

    private String id;
    private double totalCharge;
    private String customerId;
    private List<String> productIds;

    public Payment() {}

    public Payment(final String id, final double totalCharge, final String customerId, final List<String> productIds) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.customerId = customerId;
        this.productIds = productIds;
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

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(final List<String> productIds) {
        this.productIds = productIds;
    }
}
