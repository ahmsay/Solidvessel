package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService {

    private IPaymentRepository paymentRepository;

    public PaymentService(final IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Set<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public Payment getPaymentById(final String id) {
        return paymentRepository.getPaymentById(id);
    }

    @Override
    public Set<Payment> getPaymentsByIds(final Set<String> paymentIds) {
        return paymentIds.stream().map(this::getPaymentById).collect(Collectors.toSet());
    }
}
