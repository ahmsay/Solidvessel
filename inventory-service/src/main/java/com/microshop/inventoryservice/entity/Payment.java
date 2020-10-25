package com.microshop.inventoryservice.entity;

public class Payment {

    private Long id;
    private Double totalCharge;
    private Long customerId;

    public Payment() {}

    public Payment(final Long id, final Double totalCharge, final Long customerId) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(final Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }
}
