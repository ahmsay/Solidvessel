package com.shopping.accountservice.repositories;

import com.shopping.accountservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();

    Customer findById(long id);
}
