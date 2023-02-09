package com.solidvessel.account.infra.adapter.customer.db;

import com.solidvessel.account.domain.customer.model.Customer;
import com.solidvessel.account.domain.customer.port.CustomerPort;
import com.solidvessel.account.infra.adapter.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.infra.adapter.customer.db.repository.CustomerRepository;
import com.solidvessel.account.infra.adapter.customer.rest.response.CustomerResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDBAdapter implements CustomerPort {

    private final CustomerRepository customerRepository;

    public CustomerDBAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll().stream().map(CustomerJpaEntity::toResponse).toList();
    }

    @Override
    public CustomerResponse getById(Long id) {
        CustomerJpaEntity customerJpaEntity = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
        return customerJpaEntity.toResponse();
    }

    @Override
    public void add(Customer customer) {
        customerRepository.save(CustomerJpaEntity.from(customer));
    }
}
