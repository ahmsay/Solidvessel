package com.solidvessel.account.address.model;

import com.solidvessel.account.address.service.command.UpdateAddressCommand;
import com.solidvessel.shared.model.DomainModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder
public class Address extends DomainModel {

    private String customerId;
    private String name;
    private String country;
    private String city;
    private String zipCode;
    private Boolean isPrimary;

    public void update(UpdateAddressCommand command) {
        this.name = command.name();
        this.country = command.country();
        this.city = command.city();
        this.zipCode = command.zipcode();
    }

    public void setPrimary() {
        this.isPrimary = true;
    }

    public void setNonPrimary() {
        this.isPrimary = false;
    }

    public String getFullAddress() {
        return "%s %s, %s".formatted(zipCode, city, country);
    }
}
