package com.microshop.paymentservice.services;

import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    private final IProductService productService;

    public PaymentService(final IPaymentRepository paymentRepository, final IProductService productService) {
        this.paymentRepository = paymentRepository;
        this.productService = productService;
    }

    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(final Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Payment> findByCustomerId(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    @Override
    public Payment save(final Payment payment, final List<Long> productIds) {
        paymentRepository.save(payment);
        productService.setPaymentIds(payment.getId(), productIds);
        return payment;
    }
}
