package com.solidvessel.inventory.adapter.in.product.rest.request;

import com.solidvessel.inventory.product.model.ProductAttribute;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProductAttributeRequest(@NotBlank String key, @NotBlank String value) {

    public static List<ProductAttribute> toDomainModels(List<ProductAttributeRequest> attributes) {
        if (attributes == null) {
            return List.of();
        }
        return attributes.stream().map(attribute -> new ProductAttribute(attribute.key(), attribute.value())).toList();
    }
}
