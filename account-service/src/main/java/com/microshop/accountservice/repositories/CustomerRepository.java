package com.microshop.accountservice.repositories;

import com.microshop.accountservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
