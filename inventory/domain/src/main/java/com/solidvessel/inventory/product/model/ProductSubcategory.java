package com.solidvessel.inventory.product.model;

import java.util.EnumSet;
import java.util.Set;

public enum ProductSubcategory {

    COMPUTERS(ProductCategory.ELECTRONICS),
    MOBILE_PHONES(ProductCategory.ELECTRONICS),
    TABLETS(ProductCategory.ELECTRONICS),
    AUDIO(ProductCategory.ELECTRONICS),
    CAMERAS(ProductCategory.ELECTRONICS),
    TELEVISIONS(ProductCategory.ELECTRONICS),
    GAMING(ProductCategory.ELECTRONICS),
    LIVING_ROOM(ProductCategory.FURNITURE),
    BEDROOM(ProductCategory.FURNITURE),
    OFFICE(ProductCategory.FURNITURE),
    DINING(ProductCategory.FURNITURE),
    OUTDOOR_FURNITURE(ProductCategory.FURNITURE),
    MENS_CLOTHING(ProductCategory.CLOTHING),
    WOMENS_CLOTHING(ProductCategory.CLOTHING),
    KIDS_CLOTHING(ProductCategory.CLOTHING),
    SHOES(ProductCategory.CLOTHING),
    CLOTHING_ACCESSORIES(ProductCategory.CLOTHING),
    HAND_TOOLS(ProductCategory.TOOL),
    POWER_TOOLS(ProductCategory.TOOL),
    GARDEN_TOOLS(ProductCategory.TOOL),
    AUTOMOTIVE_TOOLS(ProductCategory.TOOL),
    KITCHEN_APPLIANCES(ProductCategory.HOME_APPLIANCES),
    CLEANING_APPLIANCES(ProductCategory.HOME_APPLIANCES),
    CLIMATE_APPLIANCES(ProductCategory.HOME_APPLIANCES),
    GARDEN(ProductCategory.HOME_GARDEN),
    HOME_DECOR(ProductCategory.HOME_GARDEN),
    SPORTS_EQUIPMENT(ProductCategory.SPORTS),
    FITNESS(ProductCategory.SPORTS),
    OUTDOOR_SPORTS(ProductCategory.SPORTS),
    SKINCARE(ProductCategory.BEAUTY),
    HAIRCARE(ProductCategory.BEAUTY),
    MAKEUP(ProductCategory.BEAUTY),
    ACTION_FIGURES(ProductCategory.TOYS),
    BOARD_GAMES(ProductCategory.TOYS),
    CHILDRENS_BOOKS(ProductCategory.BOOKS),
    FICTION(ProductCategory.BOOKS),
    NON_FICTION(ProductCategory.BOOKS),
    PANTRY(ProductCategory.GROCERY),
    BEVERAGES(ProductCategory.GROCERY),
    FRESH_FOOD(ProductCategory.GROCERY),
    CAR_ACCESSORIES(ProductCategory.AUTOMOTIVE),
    MOTORCYCLE(ProductCategory.AUTOMOTIVE),
    PET_FOOD(ProductCategory.PET_SUPPLIES),
    PET_ACCESSORIES(ProductCategory.PET_SUPPLIES);

    private final ProductCategory category;

    ProductSubcategory(ProductCategory category) {
        this.category = category;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public boolean belongsTo(ProductCategory category) {
        return this.category == category;
    }

    public static Set<ProductSubcategory> forCategory(ProductCategory category) {
        return EnumSet.allOf(ProductSubcategory.class).stream()
                .filter(subcategory -> subcategory.belongsTo(category))
                .collect(java.util.stream.Collectors.toUnmodifiableSet());
    }
}
