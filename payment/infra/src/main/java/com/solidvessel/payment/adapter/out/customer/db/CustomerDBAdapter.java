package com.solidvessel.payment.adapter.out.customer.db;

import com.solidvessel.payment.adapter.out.customer.db.mapper.CustomerJpaMapper;
import com.solidvessel.payment.adapter.out.customer.db.repository.CustomerRepository;
import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.customer.port.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerDBAdapter implements CustomerPort {

    private final CustomerRepository customerRepository;
    private final CustomerJpaMapper customerJpaMapper;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customerJpaMapper.toJpaEntity(customer));
    }
}
