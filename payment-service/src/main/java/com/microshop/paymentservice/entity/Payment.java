package com.microshop.paymentservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Double totalCharge;

    @NotNull
    private Long customerId;

    public Payment() {
    }

    public Payment(final Double totalCharge, final Long customerId) {
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
