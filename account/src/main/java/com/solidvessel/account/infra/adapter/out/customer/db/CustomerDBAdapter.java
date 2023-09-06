package com.solidvessel.account.infra.adapter.out.customer.db;

import com.solidvessel.account.domain.customer.model.Customer;
import com.solidvessel.account.domain.customer.port.CustomerPort;
import com.solidvessel.account.infra.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.infra.adapter.out.customer.db.repository.CustomerRepository;
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
