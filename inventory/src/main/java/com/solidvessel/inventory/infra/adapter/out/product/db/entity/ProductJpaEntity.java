package com.solidvessel.inventory.infra.adapter.out.product.db.entity;

import com.solidvessel.inventory.domain.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.domain.product.model.Product;
import com.solidvessel.inventory.domain.product.model.ProductCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "product")
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    private ProductCategory category;

    private int quantity;

    public Product toDomainModel() {
        return new Product(id, name, price, category, quantity);
    }

    public ProductDataModel toDataModel() {
        return new ProductDataModel(id, name, price, category, quantity);
    }

    public static ProductJpaEntity from(Product product) {
        return new ProductJpaEntity(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getQuantity());
    }
}
