package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Sale;

public interface SaleService {

    Iterable<Sale> findByPaymentId(Long paymentId);

    void save(Sale sale);
}
