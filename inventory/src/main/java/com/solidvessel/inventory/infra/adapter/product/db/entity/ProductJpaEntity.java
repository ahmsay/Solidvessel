package com.solidvessel.inventory.infra.adapter.product.db.entity;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.model.Product;
import com.solidvessel.inventory.domain.product.model.ProductCategory;
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

    private ProductCategory category;

    public ProductDataModel toDataModel() {
        return new ProductDataModel(id, name, price, category);
    }

    public static ProductJpaEntity from(Product product) {
        return new ProductJpaEntity(product.getId(), product.getName(), product.getPrice(), product.getCategory());
    }
}
