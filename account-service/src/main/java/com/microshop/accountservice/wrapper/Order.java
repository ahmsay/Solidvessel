package com.microshop.accountservice.wrapper;

public class Order {

    private Long id;
    private String status;
    private Long paymentId;

    public Order() {}

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
