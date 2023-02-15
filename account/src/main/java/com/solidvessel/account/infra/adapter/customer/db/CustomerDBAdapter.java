package com.solidvessel.account.infra.adapter.customer.db;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.domain.customer.model.Customer;
import com.solidvessel.account.domain.customer.port.CustomerPort;
import com.solidvessel.account.infra.adapter.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.infra.adapter.customer.db.repository.CustomerRepository;
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
    public List<CustomerDataModel> getAll() {
        return customerRepository.findAll().stream().map(CustomerJpaEntity::toDataModel).toList();
    }

    @Override
    public CustomerDataModel getById(Long id) {
        CustomerJpaEntity customerJpaEntity = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
        return customerJpaEntity.toDataModel();
    }

    @Override
    public void add(Customer customer) {
        customerRepository.save(CustomerJpaEntity.from(customer));
    }
}
