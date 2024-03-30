package com.solidvessel.account.adapter.out.address.db;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private AddressDBAdapter addressDBAdapter;

    @Test
    public void saveAddress() {
        var address = new Address("123", "home", "turkey", "eskisehir", "26000");
        var jpaEntity = addressDBAdapter.save(address);
        assertEquals("home", jpaEntity.getName());
    }

    @Test
    public void deleteAddress() {
        var address = persistEntity(new AddressJpaEntity("123", "home", "norway", "oslo", "5843"));
        addressDBAdapter.delete(address.getId());
    }
}
