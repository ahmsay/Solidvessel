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
        products.add(new Product(1, "Clipper", "Tool", 1));
        products.add(new Product(2, "Laptop", "Electronics", 1));
        products.add(new Product(3, "Phone", "Electronics", 1));
        products.add(new Product(4, "Car", "Vehicle", 2));
        products.add(new Product(5, "Spaceship", "Vehicle", 3));
        products.add(new Product(6, "Apple", "Fruit", 3));
        products.add(new Product(7, "Ice Pick", "Tool", -1));
        products.add(new Product(8, "Desk", "Furniture", -1));
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
