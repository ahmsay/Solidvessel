package com.solidvessel.payment.adapter.out.customer.db.entity;

import com.solidvessel.payment.customer.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "customer")
public class CustomerJpaEntity {

    @Id
    private String id;

    @NotNull
    private String address;

    public Customer toDomainModel() {
        return new Customer(id, address);
    }

    public static CustomerJpaEntity from(Customer customer) {
        return new CustomerJpaEntity(customer.getId(), customer.getAddress());
    }
}
