package com.microshop.accountservice.dto;

public class PaymentDTO {

    private Long id;
    private Double totalCharge;

    public PaymentDTO() {}

    public Long getId() {
        return id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }
}
