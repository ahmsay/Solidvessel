package com.solidvessel.account.adapter.out.address.db;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import com.solidvessel.account.address.model.Address;
import com.solidvessel.account.integrationtest.BaseDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressDBAdapterTest extends BaseDatabaseTest {

    @Autowired
    private AddressDBAdapter addressDBAdapter;

    @Test
    public void saveAddress() {
        var address = new Address(null, "123", "home", "turkey", "eskisehir", "26000");
        addressDBAdapter.save(address);
    }

    @Test
    public void deleteAddress() {
        var address = persistEntity(new AddressJpaEntity(null, "123", "home", "norway", "oslo", "5843"));
        addressDBAdapter.delete(address.getId(), "123");
    }
}
