package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Sale;

public interface ISaleService {

    Iterable<Sale> findByPaymentId(Long paymentId);

    void save(Sale sale);
}
