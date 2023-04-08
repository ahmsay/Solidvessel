package com.solidvessel.payment.infra.adapter.payment.db.entity;

import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.model.Payment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "payment")
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long customerId;

    private boolean accepted;

    @ElementCollection
    @CollectionTable(name = "payment_product", joinColumns = @JoinColumn(name = "payment_id"))
    private List<ProductEmbeddable> products = new ArrayList<>();

    public PaymentJpaEntity(Long customerId, boolean accepted) {
        this.customerId = customerId;
        this.accepted = accepted;
    }

    public PaymentDataModel toDataModel() {
        return new PaymentDataModel(id, customerId);
    }

    public static PaymentJpaEntity from(Payment payment) {
        return new PaymentJpaEntity(payment.getTotalCharge(), payment.getCustomerId());
    }


}
