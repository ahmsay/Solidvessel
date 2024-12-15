package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.integrationtest.BaseDatabaseTest;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.shared.query.QueryOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private ProductDBQueryAdapter productDBQueryAdapter;

    @Test
    void getProducts() {
        persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3, true));
        var products = productDBQueryAdapter.getProducts(new QueryOptions(0));
        assertEquals("macbook", products.getFirst().getName());
    }

    @Test
    void getById() {
        var productJpaEntity = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3, true));
        var product = productDBQueryAdapter.getById(productJpaEntity.getId());
        assertEquals(productJpaEntity.getId(), product.getId());
        assertEquals(productJpaEntity.getName(), product.getName());
        assertEquals(productJpaEntity.getQuantity(), product.getQuantity());
        assertEquals(productJpaEntity.getCategory(), product.getCategory());
        assertEquals(productJpaEntity.getPrice(), product.getPrice());
        assertEquals(productJpaEntity.getIsAvailableInRegion(), product.getIsAvailableInRegion());
    }

    @Test
    void getByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3, true));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", 50D, ProductCategory.CLOTHING, 5, true));
        var productJpaEntity3 = persistEntity(new ProductJpaEntity("chair", 120D, ProductCategory.FURNITURE, 2, true));
        var products = productDBQueryAdapter.getByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId(), productJpaEntity3.getId()));
        assertEquals(5, products.get(1).getQuantity());
    }

    @Test
    void getDomainModelsByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", 1200D, ProductCategory.ELECTRONICS, 3, true));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", 50D, ProductCategory.CLOTHING, 5, true));
        var productJpaEntity3 = persistEntity(new ProductJpaEntity("chair", 120D, ProductCategory.FURNITURE, 2, true));
        var products = productDBQueryAdapter.getByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId(), productJpaEntity3.getId()));
        assertEquals(5, products.get(1).getQuantity());
    }
}
