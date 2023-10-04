package com.solidvessel.account.adapter.out.customer.db.entity;

import com.solidvessel.account.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.customer.model.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "customer")
public class CustomerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private LocalDate birthDate;

    @NotNull
    private String email;

    private String phoneNumber;

    @ElementCollection
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "customer_id"))
    private List<AddressEmbeddable> addresses = new ArrayList<>();

    public CustomerDataModel toDataModel() {
        return new CustomerDataModel(id, firstName, lastName, birthDate, email, phoneNumber);
    }

    public static CustomerJpaEntity from(final Customer customer) {
        return new CustomerJpaEntity(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getBirthDate(), customer.getEmail(), customer.getPhoneNumber(), new ArrayList<>());
    }

    public void addAddress(AddressEmbeddable address) {
        addresses.add(address);
    }

    public void removeAddress(String addressName) {
        addresses.removeIf(address -> address.getName().equals(addressName));
    }
}
