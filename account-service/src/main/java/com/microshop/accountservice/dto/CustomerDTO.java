package com.microshop.accountservice.dto;

import java.util.List;

public class CustomerDTO {

    private final Long id;
    private final String name;
    private List<PaymentDTO> paymentList;
    private List<OrderDTO> orderList;

    public CustomerDTO(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerDTO(final Long id, final String name, final List<PaymentDTO> paymentList, final List<OrderDTO> orderList) {
        this.id = id;
        this.name = name;
        this.paymentList = paymentList;
        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PaymentDTO> getPaymentList() {
        return paymentList;
    }

    public List<OrderDTO> getOrderList() {
        return orderList;
    }
}
