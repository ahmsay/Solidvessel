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

    public void update(UpdateAddressCommand command) {
        this.name = command.name();
        this.country = command.country();
        this.city = command.city();
        this.zipCode = command.zipcode();
    }
}
