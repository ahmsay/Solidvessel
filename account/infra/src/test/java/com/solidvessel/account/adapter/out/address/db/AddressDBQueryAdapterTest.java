package com.solidvessel.account.adapter.out.address.db;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private AddressDBQueryAdapter addressDBQueryAdapter;

    @Test
    public void getAddresses() {
        persistEntity(new AddressJpaEntity(null, "123", "home", "norway", "oslo", "5843"));
        persistEntity(new AddressJpaEntity(null, "123", "work", "finland", "helsinki", "4757"));
        var addresses = addressDBQueryAdapter.getAddresses("123");
        assertEquals(addresses.getFirst().name(), "home");
        assertEquals(addresses.get(1).name(), "work");
    }

    @Test
    public void getByIdAndCustomerId() {
        persistEntity(new AddressJpaEntity(null, "123", "home", "norway", "oslo", "5843"));
        var isAddressAlreadyRegistered = addressDBQueryAdapter.isAddressRegistered("123", "home");
        assertTrue(isAddressAlreadyRegistered);
    }

    @Test
    public void isAddressRegistered() {
        persistEntity(new AddressJpaEntity(null, "123", "home", "norway", "oslo", "5843"));
        var isAddressAlreadyRegistered = addressDBQueryAdapter.isAddressRegistered("123", "home");
        assertTrue(isAddressAlreadyRegistered);
    }
}
