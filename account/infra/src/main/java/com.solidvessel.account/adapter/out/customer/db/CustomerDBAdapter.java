package com.solidvessel.account.adapter.out.customer.db;

import com.solidvessel.account.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.adapter.out.customer.db.repository.CustomerRepository;
import com.solidvessel.account.customer.model.Customer;
import com.solidvessel.account.customer.port.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerDBAdapter implements CustomerPort {

    private final CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(CustomerJpaEntity.from(customer));
    }
}
