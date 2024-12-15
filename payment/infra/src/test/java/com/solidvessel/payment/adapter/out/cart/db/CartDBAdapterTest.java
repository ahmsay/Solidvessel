package com.solidvessel.payment.adapter.out.cart.db;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CartDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private CartDBAdapter cartDBAdapter;

    @Test
    void saveCart() {
        var cart = Cart.newCart("123");
        cart.addProduct(new Product(1L, "table", 5D, ProductCategory.FURNITURE, 5));
        cartDBAdapter.save(cart);
    }
}
