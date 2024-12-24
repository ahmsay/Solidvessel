package com.solidvessel.payment.adapter.out.customer.db;

import com.solidvessel.payment.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.payment.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private CustomerDBQueryAdapter customerDBQueryAdapter;

    @Test
    void getByCustomerId() {
        var customerJpaEntity = persistEntity(new CustomerJpaEntity("123", "3481 Kanagawa, Japan"));
        var customer = customerDBQueryAdapter.getById("123").get();
        assertEquals(customerJpaEntity.getId(), customer.getId());
        assertEquals(customerJpaEntity.getAddress(), customer.getAddress());
    }
}
