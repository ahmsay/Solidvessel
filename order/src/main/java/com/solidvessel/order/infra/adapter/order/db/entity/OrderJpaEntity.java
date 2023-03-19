package com.solidvessel.order.infra.adapter.order.db.entity;

import com.solidvessel.order.domain.order.datamodel.OrderDataModel;
import com.solidvessel.order.domain.order.model.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String status;

    @NotNull
    private Long customerId;

    @NotNull
    private Long paymentId;

    public OrderJpaEntity(final String status, final Long customerId, final Long paymentId) {
        this.status = status;
        this.customerId = customerId;
        this.paymentId = paymentId;
    }

    public OrderDataModel toDataModel() {
        return new OrderDataModel(id, status, customerId, paymentId);
    }

    public static OrderJpaEntity from(Order order) {
        return new OrderJpaEntity(order.getStatus(), order.getCustomerId(), order.getPaymentId());
    }
}
