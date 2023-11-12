package com.solidvessel.account.adapter.out.customer.db.repository;

import com.solidvessel.account.adapter.out.customer.db.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, String> {
}
