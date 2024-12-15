package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
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
    void saveProduct() {
        var product = Product.newProduct("macbook", 1200D, ProductCategory.ELECTRONICS, 4);
        var jpaEntity = productDBAdapter.save(product);
        assertEquals("macbook", jpaEntity.getName());
    }

    @Test
    void saveProducts() {
        var product1 = Product.newProduct("macbook", 1200D, ProductCategory.ELECTRONICS, 4);
        var product2 = Product.newProduct("macnovel", 800D, ProductCategory.ELECTRONICS, 2);
        productDBAdapter.saveProducts(List.of(product1, product2));
    }

    @Test
    void delete() {
        var productJpaEntity = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3, true));
        productDBAdapter.delete(productJpaEntity.getId());
    }

    @Test
    void deleteByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3, true));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", 50D, ProductCategory.CLOTHING, 5, true));
        productDBAdapter.deleteByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId()));
    }
}
