package com.solidvessel.order.domain.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {

    private Long id;
    private String status;
    private Long customerId;
    private Long paymentId;

    public Order(String status, Long customerId, Long paymentId) {
        this.status = status;
        this.customerId = customerId;
        this.paymentId = paymentId;
    }
}
