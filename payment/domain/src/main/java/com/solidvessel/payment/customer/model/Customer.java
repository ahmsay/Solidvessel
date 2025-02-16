package com.solidvessel.payment.customer.model;

import com.solidvessel.shared.model.Equalizer;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {

    private String id;
    private String address;

    public void updateAddress(String address) {
        this.address = address;
    }

    @Generated
    @Override
    public boolean equals(Object obj) {
        return Equalizer.equals(this, obj);
    }

    @Generated
    @Override
    public int hashCode() {
        return Equalizer.hashCode(this);
    }
}
