package com.solidvessel.account.infra.adapter.customer.db.entity;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.domain.customer.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "customer")
public class CustomerJpaEntity {

    @Id
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private LocalDate birthDate;

    @NotNull
    private String email;

    private String phoneNumber;

    public CustomerDataModel toDataModel() {
        return new CustomerDataModel(id, firstName, lastName, birthDate, email, phoneNumber);
    }

    public static CustomerJpaEntity from(final Customer customer) {
        return new CustomerJpaEntity(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getBirthDate(), customer.getEmail(), customer.getPhoneNumber());
    }
}
