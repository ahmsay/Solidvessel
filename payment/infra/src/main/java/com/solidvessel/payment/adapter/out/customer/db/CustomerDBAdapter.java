package com.solidvessel.payment.adapter.out.customer.db;

import com.solidvessel.payment.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.payment.adapter.out.customer.db.repository.CustomerRepository;
import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.customer.port.CustomerPort;
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
