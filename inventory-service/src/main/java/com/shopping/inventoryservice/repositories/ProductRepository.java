package com.shopping.inventoryservice.repositories;

import com.shopping.inventoryservice.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements IProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Clipper", 2.5, "Tool", 1L));
        products.add(new Product(2L, "Laptop", 3, "Electronics", 1L));
        products.add(new Product(3L, "Phone", 5, "Electronics", 1L));
        products.add(new Product(4L, "Car", 200, "Vehicle", 2L));
        products.add(new Product(5L, "Spaceship", 500, "Vehicle", 3L));
        products.add(new Product(6L, "Apple", 499.99,"Fruit", 3L));
        products.add(new Product(7L, "Ice Pick", 53, "Tool", -1));
        products.add(new Product(8L, "Desk", 25, "Furniture", -1));
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(final long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Product> getProductsOfPayment(final long paymentId) {
        return products.stream()
                .filter(product -> product.getPaymentId() == paymentId)
                .collect(Collectors.toList());
    }
}
