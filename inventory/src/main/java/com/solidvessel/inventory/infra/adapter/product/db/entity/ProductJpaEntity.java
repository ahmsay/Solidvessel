package com.solidvessel.inventory.infra.adapter.product.db.entity;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    private String category;

    private Long paymentId;

    public ProductJpaEntity(final String name, final Double price, final String category, final Long paymentId) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.paymentId = paymentId;
    }

    public ProductDataModel toDataModel() {
        return new ProductDataModel(id, name, price, category, paymentId);
    }

    public static ProductJpaEntity from(Product product) {
        return new ProductJpaEntity(product.getName(), product.getPrice(), product.getCategory(), product.getPaymentId());
    }
}
