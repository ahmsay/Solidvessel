package com.microshop.orderservice.dto;

public class OrderDTO {

    private final Long id;
    private final String status;
    private CustomerDTO customer;
    private PaymentDTO payment;

    public OrderDTO(final Long id, final String status) {
        this.id = id;
        this.status = status;
    }

    public OrderDTO(final Long id, final String status, final CustomerDTO customer, final PaymentDTO payment) {
        this.id = id;
        this.status = status;
        this.customer = customer;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public PaymentDTO getPayment() {
        return payment;
    }
}
