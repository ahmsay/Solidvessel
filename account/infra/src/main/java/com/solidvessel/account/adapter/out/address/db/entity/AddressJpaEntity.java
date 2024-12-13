package com.solidvessel.account.adapter.out.address.db.entity;

import com.solidvessel.account.address.model.Address;
import com.solidvessel.shared.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(name = "address")
public class AddressJpaEntity extends BaseEntity {

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

    @NotNull
    private Boolean isPrimary;

    public Address toDomainModel() {
        return Address.builder()
                .id(getId())
                .createdDate(getCreatedDate())
                .lastModifiedDate(getLastModifiedDate())
                .version(getVersion())
                .customerId(customerId)
                .name(name)
                .country(country)
                .city(city)
                .zipCode(zipcode)
                .isPrimary(isPrimary)
                .build();
    }

    public static AddressJpaEntity from(Address address) {
        return AddressJpaEntity.builder()
                .id(address.getId())
                .createdDate(address.getCreatedDate())
                .lastModifiedDate(address.getLastModifiedDate())
                .version(address.getVersion())
                .customerId(address.getCustomerId())
                .name(address.getName())
                .country(address.getCountry())
                .city(address.getCity())
                .zipcode(address.getZipCode())
                .isPrimary(address.getIsPrimary())
                .build();
    }
}
