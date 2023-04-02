package com.solidvessel.account.infra.adapter.customer.db.entity;

import com.solidvessel.account.domain.customer.datamodel.AddressDataModel;
import com.solidvessel.account.domain.customer.model.Address;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressEmbeddable {

    private String name;

    private String country;

    private String city;

    private String zipcode;

    public AddressDataModel toDataModel() {
        return new AddressDataModel(name, country, city, zipcode);
    }

    public static AddressEmbeddable from(Address address) {
        return new AddressEmbeddable(address.getName(), address.getCountry(), address.getCity(), address.getZipCode());
    }
}
