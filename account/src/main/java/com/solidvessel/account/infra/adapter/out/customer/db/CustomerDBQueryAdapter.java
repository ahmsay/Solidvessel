package com.solidvessel.account.infra.adapter.out.customer.db;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.domain.customer.port.CustomerQueryPort;
import com.solidvessel.account.infra.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.infra.adapter.out.customer.db.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerDBQueryAdapter implements CustomerQueryPort {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDataModel> getAll() {
        return customerRepository.findAll().stream().map(CustomerJpaEntity::toDataModel).toList();
    }

    @Override
    public CustomerDataModel getById(Long id) {
        CustomerJpaEntity customerJpaEntity = getCustomerById(id);
        return customerJpaEntity.toDataModel();
    }

    CustomerJpaEntity getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
    }
}
