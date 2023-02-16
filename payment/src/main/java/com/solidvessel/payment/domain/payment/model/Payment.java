package com.solidvessel.payment.domain.payment.model;

public class Payment {

    private Long id;
    private Double totalCharge;
    private Long customerId;

    public Payment(Long id, Double totalCharge, Long customerId) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.customerId = customerId;
    }

    public Payment(Double totalCharge, Long customerId) {
        this.totalCharge = totalCharge;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
