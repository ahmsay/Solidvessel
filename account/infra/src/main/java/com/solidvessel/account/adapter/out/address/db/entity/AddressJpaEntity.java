package com.solidvessel.account.adapter.out.address.db.entity;

import com.solidvessel.account.address.datamodel.AddressDataModel;
import com.solidvessel.account.address.model.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "address")
public class AddressJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String customerId;

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String zipcode;

    public AddressDataModel toDataModel() {
        return new AddressDataModel(name, country, city, zipcode);
    }

    public Address toDomainModel() {
        return new Address(id, customerId, name, country, city, zipcode);
    }

    public static AddressJpaEntity from(Address address) {
        return new AddressJpaEntity(address.getId(), address.getCustomerId(), address.getName(), address.getCountry(), address.getCity(), address.getZipCode());
    }
}
