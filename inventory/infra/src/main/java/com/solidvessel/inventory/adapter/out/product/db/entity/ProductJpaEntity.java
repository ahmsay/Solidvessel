package com.solidvessel.inventory.adapter.out.product.db.entity;

import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import com.solidvessel.shared.jpa.entity.BaseEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(name = "product")
public class ProductJpaEntity extends BaseEntity {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String brand;

    @NotNull
    private String manufacturer;

    @NotNull
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    private ProductSubcategory subcategory;

    @NotNull
    private Integer quantity;

    @ElementCollection
    @CollectionTable(name = "product_attribute", joinColumns = @JoinColumn(name = "product_id"))
    @OrderColumn(name = "attribute_order")
    private List<ProductAttributeEmbeddable> attributes;

    @NotNull
    private Boolean isAvailableInRegion;
}
