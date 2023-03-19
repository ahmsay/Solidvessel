package com.solidvessel.payment.infra.adapter.payment.db.entity;

import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.model.Payment;
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
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double totalCharge;

    @NotNull
    private Long customerId;

    public PaymentJpaEntity(final Double totalCharge, final Long customerId) {
        this.totalCharge = totalCharge;
        this.customerId = customerId;
    }

    public PaymentDataModel toDataModel() {
        return new PaymentDataModel(id, totalCharge, customerId);
    }

    public static PaymentJpaEntity from(Payment payment) {
        return new PaymentJpaEntity(payment.getTotalCharge(), payment.getCustomerId());
    }
}
