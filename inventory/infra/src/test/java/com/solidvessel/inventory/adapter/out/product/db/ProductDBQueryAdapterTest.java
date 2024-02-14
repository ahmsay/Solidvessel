package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.integrationtest.BaseDatabaseTest;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.shared.query.QueryOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private ProductDBQueryAdapter productDBQueryAdapter;

    @Test
    public void getProducts() {
        persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        var products = productDBQueryAdapter.getProducts(new QueryOptions(0));
        assertEquals("macbook", products.getFirst().getName());
    }

    @Test
    public void getById() {
        var productJpaEntity = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        var product = productDBQueryAdapter.getById(productJpaEntity.getId());
        assertEquals(productJpaEntity.getId(), product.getId());
        assertEquals(productJpaEntity.getName(), product.getName());
        assertEquals(productJpaEntity.getQuantity(), product.getQuantity());
        assertEquals(productJpaEntity.getCategory(), product.getCategory());
        assertEquals(productJpaEntity.getPrice(), product.getPrice());
    }

    @Test
    public void getByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", 50D, ProductCategory.CLOTHING, 5));
        var productJpaEntity3 = persistEntity(new ProductJpaEntity("chair", 120D, ProductCategory.FURNITURE, 2));
        var products = productDBQueryAdapter.getByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId(), productJpaEntity3.getId()));
        assertEquals(5, products.get(1).getQuantity());
    }

    @Test
    public void isProductAvailableWithQuantity() {
        var productJpaEntity = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        assertTrue(productDBQueryAdapter.isAvailable(productJpaEntity.getId(), 3));
        assertFalse(productDBQueryAdapter.isAvailable(productJpaEntity.getId(), 5));
    }

    @Test
    public void getDomainModelsByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", 50D, ProductCategory.CLOTHING, 5));
        var productJpaEntity3 = persistEntity(new ProductJpaEntity("chair", 120D, ProductCategory.FURNITURE, 2));
        var products = productDBQueryAdapter.getByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId(), productJpaEntity3.getId()));
        assertEquals(5, products.get(1).getQuantity());
    }
}
