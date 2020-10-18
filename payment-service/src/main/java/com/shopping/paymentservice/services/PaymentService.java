package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    public PaymentService(final IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(final Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getPaymentsOfCustomer(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }
}
