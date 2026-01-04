package com.solidvessel.inventory.adapter.out.product.db.entity;

import com.solidvessel.inventory.adapter.out.product.db.mapper.ProductJpaMapper;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
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

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @NotNull
    private Integer quantity;

    @NotNull
    private Boolean isAvailableInRegion;

    public Product toDomainModel() {
        return ProductJpaMapper.INSTANCE.toDomainModel(this);
    }

    public static ProductJpaEntity from(Product product) {
        return ProductJpaMapper.INSTANCE.toJpaEntity(product);
    }
}
