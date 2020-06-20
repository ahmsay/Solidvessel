package com.shopping.inventoryservice.repositories;

import com.shopping.inventoryservice.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("1", "Clipper", 2.5, "Tool", "1"));
        products.add(new Product("2", "Laptop", 3, "Electronics", "1"));
        products.add(new Product("3", "Phone", 5, "Electronics", "1"));
        products.add(new Product("4", "Car", 200, "Vehicle", "2"));
        products.add(new Product("5", "Spaceship", 500, "Vehicle", "3"));
        products.add(new Product("6", "Apple", 499.99,"Fruit", "3"));
        products.add(new Product("7", "Ice Pick", 53, "Tool", "-"));
        products.add(new Product("8", "Desk", 25, "Furniture", "-"));
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(final String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
