package com.solidvessel.account.infra.adapter.customer.db.entity;

import com.solidvessel.account.domain.customer.model.Customer;
import com.solidvessel.account.infra.adapter.customer.rest.response.CustomerResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class CustomerJpaEntity {

    @Id
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public CustomerJpaEntity() {
    }

    public CustomerJpaEntity(final Long id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerResponse toResponse() {
        return new CustomerResponse(id, firstName, lastName);
    }

    public static CustomerJpaEntity from(final Customer customer) {
        return new CustomerJpaEntity(customer.getId(), customer.getFirstName(), customer.getLastName());
    }
}
