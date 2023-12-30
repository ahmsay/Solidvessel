package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.integrationtest.BaseDatabaseTest;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private ProductDBAdapter productDBAdapter;

    @Test
    public void saveProduct() {
        var product = new Product(null, "macbook", 1200D, ProductCategory.ELECTRONICS, 4);
        productDBAdapter.save(product);
    }

    @Test
    public void saveProducts() {
        var product1 = new Product(null, "macbook", 1200D, ProductCategory.ELECTRONICS, 4);
        var product2 = new Product(null, "macnovel", 800D, ProductCategory.ELECTRONICS, 2);
        productDBAdapter.saveProducts(List.of(product1, product2));
    }
}
