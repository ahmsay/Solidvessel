package com.solidvessel.account.adapter.out.address.db;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.integrationtest.BaseDatabaseTest;
import com.solidvessel.shared.query.QueryOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressDBQueryAdapterTest extends BaseDatabaseTest {

    @Autowired
    private AddressDBQueryAdapter addressDBQueryAdapter;

    @Test
    void getAddresses() {
        persistEntity(new AddressJpaEntity("123", "home", "norway", "oslo", "5843", false));
        persistEntity(new AddressJpaEntity("123", "work", "finland", "helsinki", "4757", false));
        var addresses = addressDBQueryAdapter.getAddresses("123", new QueryOptions(0));
        assertEquals(addresses.getFirst().getName(), "home");
        assertEquals(addresses.get(1).getName(), "work");
    }

    @Test
    void getByIdAndCustomerId() {
        var addressJpaEntity = persistEntity(new AddressJpaEntity("123", "home", "norway", "oslo", "5843", false));
        var address = addressDBQueryAdapter.getByIdAndCustomerId(addressJpaEntity.getId(), addressJpaEntity.getCustomerId());
        assertEquals(addressJpaEntity.getId(), address.getId());
        assertEquals(addressJpaEntity.getCustomerId(), address.getCustomerId());
        assertEquals(addressJpaEntity.getName(), address.getName());
        assertEquals(addressJpaEntity.getCountry(), address.getCountry());
        assertEquals(addressJpaEntity.getCity(), address.getCity());
        assertEquals(addressJpaEntity.getCustomerId(), address.getCustomerId());
        assertEquals(addressJpaEntity.getZipcode(), address.getZipCode());
        assertEquals(addressJpaEntity.getIsPrimary(), address.getIsPrimary());
    }

    @Test
    void isAddressRegisteredWithIdAndCustomerId() {
        var address = persistEntity(new AddressJpaEntity("123", "home", "norway", "oslo", "5843", false));
        var isAddressAlreadyRegistered = addressDBQueryAdapter.isAddressRegistered(address.getId(), address.getCustomerId());
        assertTrue(isAddressAlreadyRegistered);
    }

    @Test
    void isAddressRegisteredWithNameAndCustomerId() {
        var address = persistEntity(new AddressJpaEntity("123", "home", "norway", "oslo", "5843", false));
        var isAddressAlreadyRegistered = addressDBQueryAdapter.isAddressRegistered(address.getCustomerId(), address.getName());
        assertTrue(isAddressAlreadyRegistered);
    }

    @Test
    void countAddresses() {
        persistEntity(new AddressJpaEntity("123", "home", "norway", "oslo", "5843", false));
        int addressCount = addressDBQueryAdapter.getAddressCount("123");
        assertEquals(1, addressCount);
    }

    @Test
    void getPrimaryAddress() {
        persistEntity(new AddressJpaEntity("123", "home", "norway", "oslo", "5843", true));
        var address = addressDBQueryAdapter.getPrimaryAddress("123");
        assertEquals(true, address.getIsPrimary());
    }
}
