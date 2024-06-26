package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.integrationtest.BaseDatabaseTest;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private ProductDBAdapter productDBAdapter;

    @Test
    public void saveProduct() {
        var product = new Product("macbook", 1200D, ProductCategory.ELECTRONICS, 4);
        var jpaEntity = productDBAdapter.save(product);
        assertEquals("macbook", jpaEntity.getName());
    }

    @Test
    public void saveProducts() {
        var product1 = new Product("macbook", 1200D, ProductCategory.ELECTRONICS, 4);
        var product2 = new Product("macnovel", 800D, ProductCategory.ELECTRONICS, 2);
        productDBAdapter.saveProducts(List.of(product1, product2));
    }
}
