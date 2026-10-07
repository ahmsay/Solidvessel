package com.solidvessel.inventory.adapter.out.product.db.entity;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import com.solidvessel.shared.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(name = "product")
public class ProductJpaEntity extends BaseEntity {

    public ProductJpaEntity(String name, String description, Double price, ProductCategory category,
                            ProductSubcategory subcategory, Integer quantity, Boolean isAvailableInRegion) {
        this(name, description, null, price, category, subcategory, quantity, isAvailableInRegion);
    }

    @NotNull
    private String name;

    private String description;

    private String brand;

    @NotNull
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    private ProductSubcategory subcategory;

    @NotNull
    private Integer quantity;

    @NotNull
    private Boolean isAvailableInRegion;
}
