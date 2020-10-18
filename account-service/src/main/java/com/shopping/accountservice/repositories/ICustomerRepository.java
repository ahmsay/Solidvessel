package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();

    Optional<Customer> findById(Long id);
}
