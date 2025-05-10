package com.solidvessel.order.order.model;

import com.solidvessel.shared.model.DomainModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder
public class Order extends DomainModel {

    private OrderStatus status;
    private String customerId;
    private Long paymentId;
    private String address;
    private OrderCancellation cancellation;
    private String recipient;

    public static Order newOrder(String customerId, Long paymentId, String address) {
        return Order.builder()
                .status(OrderStatus.PREPARING)
                .customerId(customerId)
                .paymentId(paymentId)
                .address(address)
                .build();
    }

    public boolean canCancel() {
        return status == OrderStatus.PREPARING || status == OrderStatus.ON_THE_WAY;
    }

    public void cancel(CancellationReason cancellationReason, String explanation) {
        this.status = OrderStatus.CANCELLED;
        this.cancellation = new OrderCancellation(cancellationReason, explanation);
    }

    public boolean canDeliver() {
        return status == OrderStatus.ON_THE_WAY;
    }

    public void deliver(String recipient) {
        this.status = OrderStatus.DELIVERED;
        this.recipient = recipient;
    }

    public boolean canUpdateAddress() {
        return status == OrderStatus.PREPARING;
    }

    public void updateAddress(String address) {
        this.address = address;
    }
}
