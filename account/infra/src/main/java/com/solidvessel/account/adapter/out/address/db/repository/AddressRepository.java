package com.solidvessel.account.adapter.out.address.db.repository;

import com.solidvessel.account.adapter.out.address.db.entity.AddressJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressJpaEntity, Long>, PagingAndSortingRepository<AddressJpaEntity, Long> {

    List<AddressJpaEntity> findByCustomerId(String customerId, Pageable pageable);

    boolean existsByIdAndCustomerId(Long id, String customerId);

    boolean existsByNameAndCustomerId(String name, String customerId);

    Optional<AddressJpaEntity> findByIdAndCustomerId(Long id, String customerId);

    int countByCustomerId(String customerId);

    Optional<AddressJpaEntity> findByCustomerIdAndIsPrimaryIsTrue(String customerId);
}
