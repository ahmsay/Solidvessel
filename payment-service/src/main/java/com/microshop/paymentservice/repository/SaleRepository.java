package com.microshop.paymentservice.repository;

import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.entity.SaleId;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale, SaleId> {

    Iterable<Sale> findByPaymentId(Long paymentId);
}
