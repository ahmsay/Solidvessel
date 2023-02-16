package com.solidvessel.payment.infra.adapter.payment.db.entity;

import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.model.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double totalCharge;

    @NotNull
    private Long customerId;

    public PaymentJpaEntity() {
    }

    public PaymentJpaEntity(final Double totalCharge, final Long customerId) {
        this.totalCharge = totalCharge;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public PaymentDataModel toDataModel() {
        return new PaymentDataModel(id, totalCharge, customerId);
    }

    public static PaymentJpaEntity from(Payment payment) {
        return new PaymentJpaEntity(payment.getTotalCharge(), payment.getCustomerId());
    }
}
