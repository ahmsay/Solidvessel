package com.microshop.paymentservice.dto;

import com.microshop.paymentservice.entity.Customer;

public class PaymentDTO {

    private final Long id;
    private final Double totalCharge;
    private final Customer customer;

    public PaymentDTO(final Long id, final Double totalCharge, final Customer customer) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public Customer getCustomer() {
        return customer;
    }
}
