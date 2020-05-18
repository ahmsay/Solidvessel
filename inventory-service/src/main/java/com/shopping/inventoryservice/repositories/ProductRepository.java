package com.shopping.inventoryservice.repositories;

import com.shopping.inventoryservice.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ProductRepository implements IProductRepository {

    private Set<Product> products;

    public ProductRepository() {
        products = new HashSet<>();
        products.add(new Product(1, "Clipper", "Tool"));
        products.add(new Product(2, "Laptop", "Electronics"));
        products.add(new Product(3, "Phone", "Electronics"));
        products.add(new Product(4, "Car", "Vehicle"));
        products.add(new Product(5, "Spaceship", "Vehicle"));
        products.add(new Product(6, "Apple", "Fruit"));
        products.add(new Product(7, "Ice Pick", "Tool"));
        products.add(new Product(8, "Desk", "Furniture"));
    }

    @Override
    public Set<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(final int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
    }
}
