package com.solidvessel.payment.infra.adapter.payment.db.entity;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductEmbeddable {

    private Long productId;
    private int quantity;
    private String name;
    private Double price;

    public static ProductEmbeddable from(ProductDataModel product) {
        return new ProductEmbeddable(product.id(), product.quantity(), product.name(), product.price());
    }

    public ProductDataModel toDataModel() {
        return new ProductDataModel(productId, quantity, name, price);
    }
}
