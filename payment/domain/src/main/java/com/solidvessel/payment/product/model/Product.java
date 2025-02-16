package com.solidvessel.payment.product.model;

import com.solidvessel.shared.model.Equalizer;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Product implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private ProductCategory category;
    private Integer quantity;

    public void increaseQuantity(Integer quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    public double getTotalPrice() {
        return price * quantity;
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
