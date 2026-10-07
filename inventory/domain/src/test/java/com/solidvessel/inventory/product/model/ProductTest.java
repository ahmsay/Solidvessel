package com.solidvessel.inventory.product.model;

import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void createNewProduct() {
        var product = Product.newProduct("macbook", "A laptop", "Apple", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 5);
        assertNull(product.getId());
        assertEquals("macbook", product.getName());
        assertEquals("A laptop", product.getDescription());
        assertEquals(1200D, product.getPrice());
        assertEquals(ProductCategory.ELECTRONICS, product.getCategory());
        assertEquals(5, product.getQuantity());
        assertEquals(true, product.getIsAvailableInRegion());
    }

    @Test
    void createsProductWithBrand() {
        var product = Product.newProduct("macbook", "A laptop", "Apple", 1200D,
                ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 5);

        assertEquals("Apple", product.getBrand());
    }

    @Test
    void decreaseQuantity() {
        var product = new Product("macbook", "A laptop", "Apple", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 5, true);
        product.decreaseQuantity(2);
        assertEquals(3, product.getQuantity());
    }

    @Test
    void isInStock() {
        var product = new Product("macbook", "A laptop", "Apple", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 5, true);
        assertTrue(product.isAvailable(3).getIsAvailable());
        assertTrue(product.isAvailable(5).getIsAvailable());
        assertFalse(product.isAvailable(10).getIsAvailable());
        assertEquals(UnavailableReason.NOT_IN_STOCKS, product.isAvailable(10).getUnavailableReason());
    }

    @Test
    void isAvailableInRegion() {
        var product1 = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).subcategory(ProductSubcategory.MENS_CLOTHING).quantity(6).isAvailableInRegion(true).build();
        var product2 = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).subcategory(ProductSubcategory.MENS_CLOTHING).quantity(6).isAvailableInRegion(false).build();
        assertTrue(product1.isAvailable(3).getIsAvailable());
        var availability = product2.isAvailable(3);
        assertFalse(availability.getIsAvailable());
        assertEquals(UnavailableReason.NOT_AVAILABLE_IN_REGION, availability.getUnavailableReason());
    }

    @Test
    void update() {
        var product = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).subcategory(ProductSubcategory.MENS_CLOTHING).quantity(6).build();
        product.update(new UpdateProductCommand(1L, "milk", "A desk accessory", "Ikea", 15D, ProductCategory.FURNITURE, ProductSubcategory.OFFICE, 6));
        assertEquals("milk", product.getName());
        assertEquals("A desk accessory", product.getDescription());
        assertEquals(15D, product.getPrice());
        assertEquals(ProductCategory.FURNITURE, product.getCategory());
        assertEquals(6, product.getQuantity());
        assertEquals(true, product.getIsAvailableInRegion());
    }

    @Test
    void updatesBrand() {
        var product = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING)
                .subcategory(ProductSubcategory.MENS_CLOTHING).quantity(6).build();

        product.update(new UpdateProductCommand(1L, "shirt", "A shirt", "Levi's", 15D,
                ProductCategory.CLOTHING, ProductSubcategory.MENS_CLOTHING, 6));

        assertEquals("Levi's", product.getBrand());
    }

    @Test
    void changeAvailability() {
        var product = Product.builder().id(1L).name("shirt").price(5D).category(ProductCategory.CLOTHING).subcategory(ProductSubcategory.MENS_CLOTHING).quantity(6).build();
        product.changeAvailability(false);

        var availability = product.isAvailable(0);
        assertFalse(availability.getIsAvailable());
        assertEquals(UnavailableReason.NOT_AVAILABLE_IN_REGION, availability.getUnavailableReason());
    }

    @Test
    void createsProductWithSubcategory() {
        var product = Product.newProduct("phone", "A mobile phone", "Samsung", 500D, ProductCategory.ELECTRONICS,
                ProductSubcategory.MOBILE_PHONES, 2);

        assertEquals(ProductSubcategory.MOBILE_PHONES, product.getSubcategory());
    }

    @Test
    void rejectsSubcategoryFromAnotherCategory() {
        assertThrows(IllegalArgumentException.class, () -> Product.newProduct("phone", "A mobile phone", "Samsung", 500D,
                ProductCategory.ELECTRONICS, ProductSubcategory.OFFICE, 2));
    }

    @Test
    void listsSubcategoriesForCategory() {
        assertTrue(ProductSubcategory.forCategory(ProductCategory.ELECTRONICS)
                .contains(ProductSubcategory.MOBILE_PHONES));
        assertFalse(ProductSubcategory.forCategory(ProductCategory.ELECTRONICS)
                .contains(ProductSubcategory.OFFICE));
    }
}
