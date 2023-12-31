package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.product.datamodel.ProductDataModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PaymentDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private PaymentDBAdapter paymentDBAdapter;

    @Test
    public void savePayment() {
        var productsFromInventory = List.of(new ProductDataModel(1L, 3, "pants", 25D));
        var productsInCart = Map.of(1L, 2);
        var payment = Payment.newPayment("123", productsFromInventory, productsInCart);
        paymentDBAdapter.save(payment);
    }
}
