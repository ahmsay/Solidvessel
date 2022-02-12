package com.microshop.paymentservice.service;

import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.repository.SaleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Iterable<Sale> getByPaymentId(Long paymentId) {
        return saleRepository.findByPaymentId(paymentId);
    }

    public void add(final Sale sale) {
        saleRepository.save(sale);
    }
}
