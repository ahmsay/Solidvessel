package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Iterable<Sale> findByPaymentId(Long paymentId) {
        return saleRepository.findByPaymentId(paymentId);
    }

    public void save(final Sale sale) {
        saleRepository.save(sale);
    }
}
