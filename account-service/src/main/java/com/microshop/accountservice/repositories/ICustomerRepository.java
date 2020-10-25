package com.microshop.accountservice.repositories;

import com.microshop.accountservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

}
