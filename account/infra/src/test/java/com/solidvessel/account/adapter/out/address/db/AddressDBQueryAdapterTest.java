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
        assertEquals(addresses.getFirst().getName(), "home");
        assertEquals(addresses.get(1).getName(), "work");
    }

    @Test
    public void getByIdAndCustomerId() {
        var addressJpaEntity = persistEntity(new AddressJpaEntity(null, "123", "home", "norway", "oslo", "5843"));
        var address = addressDBQueryAdapter.getByIdAndCustomerId(addressJpaEntity.getId(), addressJpaEntity.getCustomerId());
        assertEquals(addressJpaEntity.getId(), address.getId());
        assertEquals(addressJpaEntity.getCustomerId(), address.getCustomerId());
        assertEquals(addressJpaEntity.getName(), address.getName());
        assertEquals(addressJpaEntity.getCountry(), address.getCountry());
        assertEquals(addressJpaEntity.getCity(), address.getCity());
        assertEquals(addressJpaEntity.getCustomerId(), address.getCustomerId());
        assertEquals(addressJpaEntity.getZipcode(), address.getZipCode());
    }

    @Test
    public void isAddressRegistered() {
        persistEntity(new AddressJpaEntity(null, "123", "home", "norway", "oslo", "5843"));
        var isAddressAlreadyRegistered = addressDBQueryAdapter.isAddressRegistered("123", "home");
        assertTrue(isAddressAlreadyRegistered);
    }
}
