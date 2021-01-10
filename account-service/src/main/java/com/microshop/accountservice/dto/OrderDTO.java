package com.microshop.accountservice.dto;

public class OrderDTO {

    private Long id;
    private String status;
    private Long paymentId;

    public OrderDTO() {}

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
