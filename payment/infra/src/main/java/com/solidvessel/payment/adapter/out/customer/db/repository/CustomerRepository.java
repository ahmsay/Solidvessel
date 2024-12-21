package com.solidvessel.payment.adapter.out.customer.db.repository;

import com.solidvessel.payment.adapter.out.customer.db.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, String> {
}
