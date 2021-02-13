package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Iterable<Sale> findByPaymentId(Long paymentId) {
        return saleRepository.findByPaymentId(paymentId);
    }

    @Override
    public void save(final Sale sale) {
        saleRepository.save(sale);
    }
}
