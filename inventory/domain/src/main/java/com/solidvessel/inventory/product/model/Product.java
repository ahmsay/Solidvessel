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
    private ProductSubcategory subcategory;
    private Integer quantity;
    @Builder.Default
    private Boolean isAvailableInRegion = true;

    public Product(String name, Double price, ProductCategory category, Integer quantity, Boolean isAvailableInRegion) {
        this(name, price, category, null, quantity, isAvailableInRegion);
    }

    public static Product newProduct(String name, Double price, ProductCategory category, Integer quantity) {
        return new Product(name, price, category, null, quantity, true);
    }

    public static Product newProduct(String name, Double price, ProductCategory category,
                                     ProductSubcategory subcategory, Integer quantity) {
        validateSubcategory(category, subcategory);
        return new Product(name, price, category, subcategory, quantity, true);
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
        validateSubcategory(command.category(), command.subcategory());
        this.subcategory = command.subcategory();
        this.quantity = command.quantity();
    }

    private static void validateSubcategory(ProductCategory category, ProductSubcategory subcategory) {
        if (subcategory != null && !subcategory.belongsTo(category)) {
            throw new IllegalArgumentException("Subcategory does not belong to the selected category.");
        }
    }

    public void changeAvailability(Boolean isAvailable) {
        this.isAvailableInRegion = isAvailable;
    }
}
