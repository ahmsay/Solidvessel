package com.solidvessel.payment.adapter.out.customer.db;

import com.solidvessel.payment.adapter.out.customer.db.mapper.CustomerJpaMapper;
import com.solidvessel.payment.adapter.out.customer.db.repository.CustomerRepository;
import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.customer.port.CustomerQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerDBQueryAdapter implements CustomerQueryPort {

    private final CustomerRepository customerRepository;
    private final CustomerJpaMapper customerJpaMapper;

    @Override
    public Optional<Customer> getById(String id) {
        return customerRepository.findById(id).map(customerJpaMapper::toDomainModel);
    }
}
