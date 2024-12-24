package com.solidvessel.payment.adapter.out.customer.db;

import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private CustomerDBAdapter customerDBAdapter;

    @Test
    void saveCustomer() {
        var customer = new Customer("123", "4752 Ankara, Türkiye");
        customerDBAdapter.save(customer);
    }
}
