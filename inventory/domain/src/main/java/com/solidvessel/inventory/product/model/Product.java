package com.solidvessel.inventory.product.model;

import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import com.solidvessel.shared.model.DomainModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder
public class Product extends DomainModel {

    private String name;
    private Double price;
    private ProductCategory category;
    private Integer quantity;
    @Builder.Default
    private Boolean isAvailableInRegion = true;

    public static Product newProduct(String name, Double price, ProductCategory category, Integer quantity) {
        return new Product(name, price, category, quantity, true);
    }

    public void decreaseQuantity(Integer boughtQuantity) {
        quantity = quantity - boughtQuantity;
    }

    private ProductAvailability isAvailableInStock(Integer desiredQuantity) {
        if (desiredQuantity > quantity) {
            return ProductAvailability.notInStocks();
        }
        return ProductAvailability.available();
    }

    public ProductAvailability isAvailable(Integer desiredQuantity) {
        if (!isAvailableInRegion) {
            return ProductAvailability.notAvailableInRegion();
        }
        return isAvailableInStock(desiredQuantity);
    }

    public void update(UpdateProductCommand command) {
        this.name = command.name();
        this.price = command.price();
        this.category = command.category();
        this.quantity = command.quantity();
    }

    public void changeAvailability(Boolean isAvailable) {
        this.isAvailableInRegion = isAvailable;
    }
}
