package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.integrationtest.BaseDatabaseTest;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private ProductDBAdapter productDBAdapter;

    @Test
    void saveProduct() {
        var product = Product.newProduct("macbook", "A laptop", "Apple", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4);
        var jpaEntity = productDBAdapter.save(product);
        assertEquals("macbook", jpaEntity.getName());
        assertEquals("A laptop", jpaEntity.getDescription());
    }

    @Test
    void saveProducts() {
        var product1 = Product.newProduct("macbook", "A laptop", "Apple", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4);
        var product2 = Product.newProduct("macnovel", "A novel laptop", "Lenovo", 800D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 2);
        productDBAdapter.saveProducts(List.of(product1, product2));
    }

    @Test
    void delete() {
        var productJpaEntity = persistEntity(new ProductJpaEntity("macbook", "A laptop", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, true));
        productDBAdapter.delete(productJpaEntity.getId());
    }

    @Test
    void deleteByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", "A laptop", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, true));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", "A pair of shorts", 50D, ProductCategory.CLOTHING, ProductSubcategory.MENS_CLOTHING, 5, true));
        productDBAdapter.deleteByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId()));
    }
}
