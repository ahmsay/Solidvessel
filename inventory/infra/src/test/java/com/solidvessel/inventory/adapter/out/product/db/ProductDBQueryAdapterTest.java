package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductAttributeEmbeddable;
import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.integrationtest.BaseDatabaseTest;
import com.solidvessel.inventory.product.model.ProductAttribute;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import com.solidvessel.shared.query.QueryOptions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private ProductDBQueryAdapter productDBQueryAdapter;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void listQueriesDoNotLoadAttributes() {
        var id = persistProductWithAttributes();
        var statistics = testEntityManager.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class).getStatistics();
        statistics.setStatisticsEnabled(true);
        statistics.clear();

        var products = productDBQueryAdapter.getProducts(new QueryOptions(0));
        var productsByIds = productDBQueryAdapter.getByIds(List.of(id));

        assertEquals(List.of(), products.getFirst().getAttributes());
        assertEquals(List.of(), productsByIds.getFirst().getAttributes());
        assertEquals(0, statistics.getCollectionLoadCount());
        statistics.setStatisticsEnabled(false);
    }

    @Test
    void singleAndWriteQueriesLoadAttributesInOrder() {
        var id = persistProductWithAttributes();
        var attributes = List.of(new ProductAttribute("RAM", "16 GB"), new ProductAttribute("Color", "Silver"));

        assertEquals(attributes, productDBQueryAdapter.getById(id).getAttributes());
        testEntityManager.clear();
        assertEquals(attributes, productDBQueryAdapter.getByIdsWithAttributes(List.of(id)).getFirst().getAttributes());
    }

    private Long persistProductWithAttributes() {
        var attributes = List.of(new ProductAttributeEmbeddable("RAM", "16 GB"), new ProductAttributeEmbeddable("Color", "Silver"));
        var id = persistEntity(new ProductJpaEntity("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, attributes, true)).getId();
        testEntityManager.flush();
        testEntityManager.clear();
        return id;
    }

    @Test
    void getProducts() {
        persistEntity(new ProductJpaEntity("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, List.of(), true));
        var products = productDBQueryAdapter.getProducts(new QueryOptions(0));
        assertEquals("macbook", products.getFirst().getName());
    }

    @Test
    void getById() {
        var productJpaEntity = persistEntity(new ProductJpaEntity("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, List.of(), true));
        var product = productDBQueryAdapter.getById(productJpaEntity.getId());
        assertEquals(productJpaEntity.getId(), product.getId());
        assertEquals(productJpaEntity.getName(), product.getName());
        assertEquals(productJpaEntity.getDescription(), product.getDescription());
        assertEquals(productJpaEntity.getManufacturer(), product.getManufacturer());
        assertEquals(productJpaEntity.getQuantity(), product.getQuantity());
        assertEquals(productJpaEntity.getCategory(), product.getCategory());
        assertEquals(productJpaEntity.getPrice(), product.getPrice());
        assertEquals(productJpaEntity.getIsAvailableInRegion(), product.getIsAvailableInRegion());
    }

    @Test
    void getByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, List.of(), true));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", "A pair of shorts", "Levi's", "Levi Strauss & Co.", 50D, ProductCategory.CLOTHING, ProductSubcategory.MENS_CLOTHING, 5, List.of(), true));
        var productJpaEntity3 = persistEntity(new ProductJpaEntity("chair", "A living room chair", "Ikea", "Ikea", 120D, ProductCategory.FURNITURE, ProductSubcategory.LIVING_ROOM, 2, List.of(), true));
        var products = productDBQueryAdapter.getByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId(), productJpaEntity3.getId()));
        assertEquals(5, products.get(1).getQuantity());
    }

    @Test
    void getDomainModelsByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, List.of(), true));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", "A pair of shorts", "Levi's", "Levi Strauss & Co.", 50D, ProductCategory.CLOTHING, ProductSubcategory.MENS_CLOTHING, 5, List.of(), true));
        var productJpaEntity3 = persistEntity(new ProductJpaEntity("chair", "A living room chair", "Ikea", "Ikea", 120D, ProductCategory.FURNITURE, ProductSubcategory.LIVING_ROOM, 2, List.of(), true));
        var products = productDBQueryAdapter.getByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId(), productJpaEntity3.getId()));
        assertEquals(5, products.get(1).getQuantity());
    }
}
