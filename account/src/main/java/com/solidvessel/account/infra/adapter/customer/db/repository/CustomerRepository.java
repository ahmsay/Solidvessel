package com.solidvessel.account.infra.adapter.customer.db.repository;

import com.solidvessel.account.infra.adapter.customer.db.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {
}
