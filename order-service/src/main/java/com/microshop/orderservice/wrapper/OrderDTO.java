package com.microshop.orderservice.wrapper;

public class OrderDTO {

    private final Long id;
    private final String status;
    private final Customer customer;
    private final Payment payment;

    public OrderDTO(final long id, final String status, final Customer customer, final Payment payment) {
        this.id = id;
        this.status = status;
        this.customer = customer;
        this.payment = payment;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Payment getPayment() {
        return payment;
    }
}
