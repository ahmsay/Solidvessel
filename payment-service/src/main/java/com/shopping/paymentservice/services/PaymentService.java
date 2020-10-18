package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Payment> getPaymentById(final Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getPaymentsOfCustomer(final Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }
}
