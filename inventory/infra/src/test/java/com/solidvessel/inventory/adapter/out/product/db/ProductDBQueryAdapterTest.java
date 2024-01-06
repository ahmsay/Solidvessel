package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.integrationtest.BaseDatabaseTest;
import com.solidvessel.inventory.product.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private ProductDBQueryAdapter productDBQueryAdapter;

    @Test
    public void getAll() {
        persistEntity(new ProductJpaEntity(null, "macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        var products = productDBQueryAdapter.getAll();
        assertEquals("macbook", products.getFirst().name());
    }

    @Test
    public void getById() {
        var productJpaEntity = persistEntity(new ProductJpaEntity(null, "macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        var product = productDBQueryAdapter.getById(productJpaEntity.getId());
        assertEquals("macbook", product.name());
    }

    @Test
    public void getDataModelsByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity(null, "macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity(null, "shorts", 50D, ProductCategory.CLOTHING, 5));
        var productJpaEntity3 = persistEntity(new ProductJpaEntity(null, "chair", 120D, ProductCategory.FURNITURE, 2));
        var products = productDBQueryAdapter.getByIds(Set.of(productJpaEntity1.getId(), productJpaEntity2.getId(), productJpaEntity3.getId()));
        assertEquals(5, products.get(1).getQuantity());
    }

    @Test
    public void isProductAvailableWithQuantity() {
        var productJpaEntity = persistEntity(new ProductJpaEntity(null, "macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        assertTrue(productDBQueryAdapter.isAvailable(productJpaEntity.getId(), 3));
        assertFalse(productDBQueryAdapter.isAvailable(productJpaEntity.getId(), 5));
    }

    @Test
    public void getDomainModelsByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity(null, "macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity(null, "shorts", 50D, ProductCategory.CLOTHING, 5));
        var productJpaEntity3 = persistEntity(new ProductJpaEntity(null, "chair", 120D, ProductCategory.FURNITURE, 2));
        var products = productDBQueryAdapter.getByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId(), productJpaEntity3.getId()));
        assertEquals(5, products.get(1).quantity());
    }
}