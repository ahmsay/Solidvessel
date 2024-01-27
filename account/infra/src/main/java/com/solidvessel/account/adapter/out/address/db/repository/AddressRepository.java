package com.solidvessel.account.adapter.out.address.db.repository;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressJpaEntity, Long> {

    List<AddressJpaEntity> findByCustomerId(String customerId);

    boolean existsByIdAndCustomerId(Long id, String customerId);

    boolean existsByNameAndCustomerId(String name, String customerId);

    void deleteByIdAndCustomerId(Long id, String customerId);

    Optional<AddressJpaEntity> findByIdAndCustomerId(Long id, String customerId);
}
