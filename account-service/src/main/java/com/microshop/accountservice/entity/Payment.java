package com.microshop.accountservice.entity;

public class Payment {

    private Long id;
    private Double totalCharge;

    public Payment() { }

    public Payment(final Long id, final Double totalCharge) {
        this.id = id;
        this.totalCharge = totalCharge;
    }

    public Long getId() {
        return id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }
}
