package com.solidvessel.account.infra.adapter.customer.db.entity;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.domain.customer.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerJpaEntity {

    @Id
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public CustomerDataModel toDataModel() {
        return new CustomerDataModel(id, firstName, lastName);
    }

    public static CustomerJpaEntity from(final Customer customer) {
        return new CustomerJpaEntity(customer.getId(), customer.getFirstName(), customer.getLastName());
    }
}
