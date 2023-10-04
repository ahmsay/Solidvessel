package com.solidvessel.inventory.adapter.out.product.db.entity;

import com.solidvessel.inventory.product.datamodel.ProductDataModel;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @NotNull
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
