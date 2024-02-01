package com.solidvessel.inventory.adapter.out.product.db.entity;

import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.shared.persistence.BaseEntity;
import jakarta.persistence.*;
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
        return Product.builder()
                .id(getId())
                .createdDate(getCreatedDate())
                .lastModifiedDate(getLastModifiedDate())
                .version(getVersion())
                .name(name)
                .price(price)
                .category(category)
                .quantity(quantity)
                .build();
    }

    public static ProductJpaEntity from(Product product) {
        return ProductJpaEntity.builder()
                .id(product.getId())
                .createdDate(product.getCreatedDate())
                .lastModifiedDate(product.getLastModifiedDate())
                .version(product.getVersion())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .build();
    }
}
