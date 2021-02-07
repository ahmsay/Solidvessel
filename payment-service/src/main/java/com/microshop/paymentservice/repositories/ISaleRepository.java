package com.microshop.paymentservice.repositories;

import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.entity.SaleId;
import org.springframework.data.repository.CrudRepository;

public interface ISaleRepository extends CrudRepository<Sale, SaleId> {

    Iterable<Sale> findByPaymentId(Long paymentId);
}
