package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.integrationtest.BaseDatabaseTest;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.model.ProductAttribute;
import com.solidvessel.inventory.product.model.ProductCategory;
import com.solidvessel.inventory.product.model.ProductSubcategory;
import com.solidvessel.inventory.product.service.command.UpdateProductCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private ProductDBAdapter productDBAdapter;

    @Autowired
    private ProductDBQueryAdapter productDBQueryAdapter;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void saveProduct() {
        var product = Product.newProduct("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4, List.of());
        var jpaEntity = productDBAdapter.save(product);
        assertEquals("macbook", jpaEntity.getName());
        assertEquals("A laptop", jpaEntity.getDescription());
        assertEquals("Foxconn", jpaEntity.getManufacturer());
    }

    @Test
    void saveAndUpdateProductAttributes() {
        var attributes = List.of(new ProductAttribute("RAM", "16 GB"), new ProductAttribute("Storage", "512 GB"), new ProductAttribute("Color", "Silver"));
        var saved = productDBAdapter.save(Product.newProduct("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4, attributes));
        testEntityManager.flush();
        testEntityManager.clear();

        var product = productDBQueryAdapter.getById(saved.getId());
        assertEquals(attributes, product.getAttributes());

        var updatedAttributes = List.of(new ProductAttribute("Color", "Black"));
        product.update(new UpdateProductCommand(product.getId(), "macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4, updatedAttributes));
        productDBAdapter.save(product);
        testEntityManager.flush();
        testEntityManager.clear();

        assertEquals(updatedAttributes, productDBQueryAdapter.getById(saved.getId()).getAttributes());
    }

    @Test
    void savingBoughtProductsKeepsAttributes() {
        var attributes = List.of(new ProductAttribute("RAM", "16 GB"), new ProductAttribute("Color", "Silver"));
        var saved = productDBAdapter.save(Product.newProduct("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4, attributes));
        testEntityManager.flush();
        testEntityManager.clear();

        var products = productDBQueryAdapter.getByIdsWithAttributes(List.of(saved.getId()));
        products.getFirst().decreaseQuantity(1);
        productDBAdapter.saveProducts(products);
        testEntityManager.flush();
        testEntityManager.clear();

        var product = productDBQueryAdapter.getById(saved.getId());
        assertEquals(3, product.getQuantity());
        assertEquals(attributes, product.getAttributes());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void handlesAttributesWithoutSurroundingTransaction() {
        var attributes = List.of(new ProductAttribute("RAM", "16 GB"));
        var saved = productDBAdapter.save(Product.newProduct("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4, attributes));
        assertEquals(attributes, saved.getAttributes());

        var product = productDBQueryAdapter.getById(saved.getId());
        assertEquals(attributes, product.getAttributes());
        assertEquals(List.of(), productDBQueryAdapter.getByIds(List.of(saved.getId())).getFirst().getAttributes());

        var updatedAttributes = List.of(new ProductAttribute("Color", "Black"));
        product.update(new UpdateProductCommand(product.getId(), "macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4, updatedAttributes));
        assertEquals(updatedAttributes, productDBAdapter.save(product).getAttributes());

        var products = productDBQueryAdapter.getByIdsWithAttributes(List.of(saved.getId()));
        products.getFirst().decreaseQuantity(1);
        productDBAdapter.saveProducts(products);
        assertEquals(updatedAttributes, productDBQueryAdapter.getById(saved.getId()).getAttributes());

        productDBAdapter.delete(saved.getId());
    }

    @Test
    void deleteProductWithAttributes() {
        var saved = productDBAdapter.save(Product.newProduct("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4,
                List.of(new ProductAttribute("RAM", "16 GB"))));
        testEntityManager.flush();
        testEntityManager.clear();

        productDBAdapter.delete(saved.getId());
        testEntityManager.flush();

        assertNull(find(saved.getId(), ProductJpaEntity.class));
    }

    @Test
    void saveProducts() {
        var product1 = Product.newProduct("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 4, List.of());
        var product2 = Product.newProduct("macnovel", "A novel laptop", "Lenovo", "Lenovo", 800D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 2, List.of());
        productDBAdapter.saveProducts(List.of(product1, product2));
    }

    @Test
    void delete() {
        var productJpaEntity = persistEntity(new ProductJpaEntity("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, List.of(), true));
        productDBAdapter.delete(productJpaEntity.getId());
    }

    @Test
    void deleteByIds() {
        var productJpaEntity1 = persistEntity(new ProductJpaEntity("macbook", "A laptop", "Apple", "Foxconn", 1200D, ProductCategory.ELECTRONICS, ProductSubcategory.COMPUTERS, 3, List.of(), true));
        var productJpaEntity2 = persistEntity(new ProductJpaEntity("shorts", "A pair of shorts", "Levi's", "Levi Strauss & Co.", 50D, ProductCategory.CLOTHING, ProductSubcategory.MENS_CLOTHING, 5, List.of(), true));
        productDBAdapter.deleteByIds(List.of(productJpaEntity1.getId(), productJpaEntity2.getId()));
    }
}
